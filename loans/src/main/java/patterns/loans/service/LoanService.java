package patterns.loans.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import patterns.loans.data.dto.request.CreateLoanDto;
import patterns.loans.data.dto.request.PurchaseDto;
import patterns.loans.data.dto.response.LoanDto;
import patterns.loans.data.entity.LoanEntity;
import patterns.loans.data.entity.TariffEntity;
import patterns.loans.data.enums.OperationType;
import patterns.loans.data.enums.Status;
import patterns.loans.data.repository.LoanRepository;
import patterns.loans.data.repository.TariffRepository;
import patterns.loans.service.communication.AccountServiceClient;
import patterns.loans.service.communication.RedisPublisher;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final TariffService tariffService;
    private final OperationService operationService;
    private final AccountServiceClient accountServiceClient;
    private final RedisPublisher redisPublisher;

    public List<LoanDto> getAllLoans() {
        return StreamSupport.stream(loanRepository.findAll().spliterator(), false)
                .map(this::hydrateLoanDto)
                .collect(Collectors.toList());
    }

    public List<LoanDto> getAllLoansByUserId(String userId) {
        return loanRepository.findAllByUserId(userId).stream()
                .map(this::hydrateLoanDto)
                .collect(Collectors.toList());
    }

    public LoanDto getLoanById(String loanId, String userId) {
        if (userId != null) {
            if (!checkLoanUser(loanId, userId)) {
                throw new RuntimeException("this is not yours loan!");
            }
        }
        LoanEntity loanEntity = loanRepository.findById(loanId).orElse(null);
        return loanEntity != null ? hydrateLoanDto(loanEntity) : null;
    }

    public void createLoan(CreateLoanDto dto, String userId) {
        LoanEntity loanEntity = loanRepository.save(hydrateLoanEntity(dto, userId));
        redisPublisher.publish("loans", loanEntity.getId());
    }

    public boolean turnAutodebtOn(String loanId, String accountId) {
        LoanEntity loanEntity = loanRepository.findById(loanId).orElse(null);
        if (loanEntity != null) {
            loanEntity.setAccountId(accountId);
            loanEntity.setAutodebt(true);
            loanRepository.save(loanEntity);
            redisPublisher.publish("loans", loanEntity.getId());
            return true;
        }
        return false;
    }

    public boolean turnAutodebtOff(String loanId) {
        LoanEntity loanEntity = loanRepository.findById(loanId).orElse(null);
        if (loanEntity != null) {
            loanEntity.setAccountId(null);
            loanEntity.setAutodebt(false);
            loanRepository.save(loanEntity);
            redisPublisher.publish("loans", loanEntity.getId());
            return true;
        }
        return false;
    }

    public boolean checkLoanUser(String loanId, String userId) {
        LoanEntity account = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        return account.getUserId().equals(userId);
    }

    public boolean purchaseLoan(String loanId, PurchaseDto dto, boolean isAutodebt)
    {
        LoanEntity loan  = loanRepository.findById(loanId).orElse(null);
        if (loan != null) {
            if (loan.getStatus() == Status.CLOSE) {
                throw new IllegalStateException("Loan is already closed");
            }
            int amountToPay = Math.min(dto.getMoney(), loan.getDebt());
            loan.setDebt(loan.getDebt() - amountToPay);
            loan.setPayout(loan.getPayout() + amountToPay);

            if (loan.getDebt() == 0) {
                loan.setStatus(Status.CLOSE);
                if (loan.getCloseDate().isAfter(LocalDate.now())) {
                    loan.setCloseDate(LocalDate.now());
                }
            }
            loanRepository.save(loan);
            redisPublisher.publish("loans", loan.getId());
            operationService.logOperation(dto.getAccountId(), loanId,
                    OperationType.REPLENISHMENT, amountToPay, loan.getDebt());
            if (isAutodebt) {
                accountServiceClient.OperationAutodebitting(dto.getAccountId(), amountToPay);
            }
            if (Objects.equals(loan.getCloseDate(), LocalDate.now())) {
                operationService.logOperation(dto.getAccountId(), loanId,
                        OperationType.CLOSING, amountToPay, loan.getDebt());
            }
            return true;
        }
        return false;
    }

    public void latePurchase(LoanEntity loan) {
        if (loan != null) {
            if (Objects.equals(loan.getCloseDate(), LocalDate.now())) {
                loan.setStatus(Status.CLOSE);
                operationService.logOperation(loan.getAccountId(), loan.getId(),
                        OperationType.CLOSING, 0, loan.getDebt());
                operationService.logOperation(loan.getAccountId(), loan.getId(),
                        OperationType.LATING, 0, loan.getDebt());
            }
        }
    }

    @Scheduled(cron = "0 0 0 * * ?")
    private void processAutoDebits() {
        List<LoanEntity> loans = loanRepository.findAllByAutodebt(true);
        for (LoanEntity loan : loans) {
            purchaseLoan(loan.getId(), new PurchaseDto(loan.getAccountId(),
                    (int)(loan.getDebt() / ChronoUnit.DAYS.between(LocalDate.now(), loan.getCloseDate()))),
                    true);
        }
    }

    @Scheduled(cron = "0 1 0 * * ?")
    private void lateLoans() {
        List<LoanEntity> loans = StreamSupport.stream(loanRepository.findAll().spliterator(), false).toList();
        for (LoanEntity loan : loans) {
            latePurchase(loan);
        }
    }
    
    private LoanDto hydrateLoanDto(LoanEntity loanEntity) {
        LoanDto loanDto = new LoanDto();
        loanDto.setId(loanEntity.getId());
        loanDto.setUserId(loanEntity.getUserId());
        loanDto.setAccountId(loanEntity.getAccountId());
        loanDto.setOpenDate(loanEntity.getOpenDate());
        loanDto.setCloseDate(loanEntity.getCloseDate());
        loanDto.setAutodebt(loanEntity.isAutodebt());
        loanDto.setPayout(loanEntity.getPayout());
        loanDto.setDebt(loanEntity.getDebt());
        loanDto.setStatus(loanEntity.getStatus());
        loanDto.setSize(loanEntity.getSize());
        loanDto.setProcents(loanEntity.getProcents());
        loanDto.setTariff(tariffService.hydrateTariffSmallDto(loanEntity.getTariff()));
        return loanDto;
    }

    private LoanEntity hydrateLoanEntity(CreateLoanDto dto, String userId) {
        LoanEntity loanEntity = new LoanEntity();
        TariffEntity tariff = tariffService.getTariffById(dto.getTariffId());
        if (tariff != null) {
            loanEntity.setTariff(tariff);
            loanEntity.setDebt(dto.getSize());
            loanEntity.setProcents(0);
            loanEntity.setPayout(0);
        }
        else {
            throw new RuntimeException("Tariff not found");
        }
        loanEntity.setId(String.valueOf(UUID.randomUUID()));
        loanEntity.setSize(dto.getSize());
        loanEntity.setAccountId(dto.getAccountId());
        loanEntity.setCloseDate(dto.getCloseDate());
        loanEntity.setUserId(userId);
        loanEntity.setAutodebt(false);
        loanEntity.setStatus(Status.OPEN);
        loanEntity.setOpenDate(LocalDate.now());
        return loanEntity;
    }
}

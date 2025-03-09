package patterns.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import patterns.core.data.dto.response.AccountDto;
import patterns.core.data.entity.AccountEntity;
import patterns.core.data.enums.OperationType;
import patterns.core.data.enums.Status;
import patterns.core.data.repository.AccountRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final OperationService operationService;

    public boolean checkAccount(String accountId, String userId) {
        AccountEntity account = accountRepository.findById(accountId).orElse(null);
        if (account != null) {
            if (account.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public List<AccountDto> getAllAccounts() {
        return StreamSupport.stream(accountRepository.findAll().spliterator(), false)
                .map(this::hydrateAccountDto)
                .collect(Collectors.toList());
    }

    public List<AccountDto> getAllAccountsByUserId(String userId) {
        return accountRepository.findAllByUserId(userId).stream()
                .map(this::hydrateAccountDto)
                .collect(Collectors.toList());
    }

    public AccountDto getAccountById(String accountId, String userId) {
        if (userId != null) {
            if (!checkAccountUser(accountId, userId)) {
                throw new RuntimeException("this is not your account!");
            }
        }
        AccountEntity account = accountRepository.findById(accountId).orElse(null);
        return account != null ? hydrateAccountDto(account) : null;
    }

    public void createAccount(String userId) {
        AccountEntity entity = accountRepository.save(hydrateAccountEntity(userId));
        operationService.logOperation(entity.getId(), OperationType.OPENING, 0, 0);
    }

    public void delete(String accountId) {
        accountRepository.deleteById(accountId);
        operationService.logOperation(accountId, OperationType.CLOSING, 0, 0);
    }

    public void withdraw(String accountId, int money) {
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("account not found"));
        account.setBalance(account.getBalance() - money);
        accountRepository.save(account);
        operationService.logOperation(accountId, OperationType.WITHDRAWING, money, account.getBalance());
    }

    public void replenish(String accountId, int money) {
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("account not found"));
        account.setBalance(account.getBalance() + money);
        accountRepository.save(account);
        operationService.logOperation(accountId, OperationType.REPLENISHMENT, money, account.getBalance());
    }

    public boolean checkAccountUser(String accountId, String userId) {
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("account not found"));
        return account.getUserId().equals(userId);
    }

    private AccountEntity hydrateAccountEntity(String userId)
    {
        AccountEntity account = new AccountEntity();
        account.setId(String.valueOf(UUID.randomUUID()));
        account.setBalance(0);
        account.setUserId(userId);
        account.setOpenDate(LocalDate.now());
        account.setCloseDate(null);
        return account;
    }

    private AccountDto hydrateAccountDto(AccountEntity account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setBalance(account.getBalance());
        accountDto.setUserId(account.getUserId());
        accountDto.setStatus(account.getCloseDate() == null ? Status.OPEN : Status.CLOSE);
        accountDto.setOpenDate(account.getOpenDate());
        accountDto.setCloseDate(account.getCloseDate());
        return accountDto;
    }
}

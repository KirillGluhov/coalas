package patterns.loans.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import patterns.loans.data.document.OperationDocument;
import patterns.loans.data.enums.OperationType;
import patterns.loans.data.repository.OperationRepository;
import patterns.loans.service.communication.RedisPublisher;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperationService {
    @Lazy
    @Autowired
    private LoanService loanService;
    private final OperationRepository operationRepository;
    private final RedisPublisher redisPublisher;

    public void logOperation(String accountId, String loanId, OperationType type, int sum, int balance) {
        OperationDocument operation = new OperationDocument();
        operation.setId(String.valueOf(UUID.randomUUID()));
        operation.setLoanId(loanId);
        operation.setAccountId(accountId);
        operation.setDate(LocalDateTime.now());
        operation.setType(type);
        operation.setSum(sum);
        operation.setBalance(balance);
        operationRepository.save(operation);
        redisPublisher.publish("loans_operations", loanId);
    }

    public List<OperationDocument> getOperationsByLoanId(String loanId, String userId) {
        if (userId != null) {
            if (!loanService.checkLoanUser(loanId, userId)) {
                throw new RuntimeException("this is not your loan!");
            }
        }
        return operationRepository.findByLoanId(loanId);
    }
}

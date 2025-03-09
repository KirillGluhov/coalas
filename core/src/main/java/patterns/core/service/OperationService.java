package patterns.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;
import patterns.core.data.document.OperationDocument;
import patterns.core.data.dto.response.AccountDto;
import patterns.core.data.entity.AccountEntity;
import patterns.core.data.enums.OperationType;
import patterns.core.data.repository.OperationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OperationService {

    @Lazy
    @Autowired
    private AccountService accountService;
    private final OperationRepository operationRepository;

    public void logAutodebtOperation(String accountId, int sum)
    {
        AccountDto account = accountService.getAccountById(accountId, null);
        if (account != null)
        {
            logOperation(accountId,OperationType.AUTODEBITING ,sum, account.getBalance());
        }
    }

    public void logOperation(String accountId, OperationType type, int sum, int balance) {
        OperationDocument operation = new OperationDocument();
        operation.setId(String.valueOf(UUID.randomUUID()));
        operation.setAccountId(accountId);
        operation.setDate(LocalDateTime.now());
        operation.setType(type);
        operation.setSum(sum);
        operation.setBalance(balance);
        operationRepository.save(operation);
    }

    public List<OperationDocument> getOperationsByAccountId(String accountId, String userId) {
        if (userId != null) {
            if (!accountService.checkAccountUser(accountId, userId)) {
                throw new RuntimeException("this is not yours account!");
            }
        }
        return operationRepository.findByAccountId(accountId);
    }
}


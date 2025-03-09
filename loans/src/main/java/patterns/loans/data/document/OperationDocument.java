package patterns.loans.data.document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import patterns.loans.data.enums.OperationType;

import java.time.LocalDateTime;

@Document(collection = "loan_operations")
@Getter
@Setter
public class OperationDocument {
    @Id
    private String id;
    private String loanId;
    private String accountId;
    private LocalDateTime date;
    private int sum;
    private int balance;
    private OperationType type;
}

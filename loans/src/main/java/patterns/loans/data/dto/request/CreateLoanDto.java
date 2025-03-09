package patterns.loans.data.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateLoanDto {
    private String tariffId;
    private String accountId;
    private int size;
    private LocalDate closeDate;
}

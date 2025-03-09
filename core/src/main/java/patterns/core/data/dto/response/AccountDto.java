package patterns.core.data.dto.response;

import lombok.Getter;
import lombok.Setter;
import patterns.core.data.enums.Status;

import java.time.LocalDate;

@Getter
@Setter
public class AccountDto {
    private String id;
    private LocalDate openDate;
    private LocalDate closeDate;
    private Status status;
    private int balance;
    private String userId;
}

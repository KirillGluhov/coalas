package patterns.users.data.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PassportUpdateDto {
    private String series;
    private String number;
    private LocalDate issueDate;
    private String departmentCode;
    private String departmentName;
}

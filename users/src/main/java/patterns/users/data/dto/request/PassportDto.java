package patterns.users.data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassportDto {
    private String series;
    private String number;
    private String issueDate;
    private String departmentCode;
    private String departmentName;
}

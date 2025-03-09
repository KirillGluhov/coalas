package patterns.loans.data.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TariffDto {
    private String id;
    private String employeeId;
    private String name;
    private double procent;
}

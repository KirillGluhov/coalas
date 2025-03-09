package patterns.loans.data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTariffDto {
    private String name;
    private double procent;
}

package patterns.loans.data.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import patterns.loans.data.entity.TariffEntity;
import patterns.loans.data.enums.Status;

import java.time.LocalDate;

@Getter
@Setter
public class LoanDto {
    private String id;
    private TariffSmallDto tariff;
    private String accountId;
    private int size;
    private LocalDate openDate;
    private LocalDate closeDate;
    private Status status;
    private int procents;
    private int debt;
    private int payout;
    private String userId;
    private boolean isAutodebt;
}

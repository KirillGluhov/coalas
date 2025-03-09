package patterns.loans.data.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import patterns.loans.data.enums.Status;

import java.time.LocalDate;

@Entity
@Table(name = "loan")
@Getter
@Setter
public class LoanEntity {
    @Id
    private String id;
    @ManyToOne
    private TariffEntity tariff;
    @Column(name = "account_id")
    @Nullable
    private String accountId;
    private int size;
    @Column(name = "open_date")
    private LocalDate openDate;
    @Column(name = "close_date")
    private LocalDate closeDate;
    private Status status;
    private int procents; //how much was increased
    private int debt; // how much u should pay
    private int payout; // how mush you have already paid
    @Column(name = "user_id")
    private String userId;
    @Column(name = "is_autodebt")
    private boolean isAutodebt;
}

package patterns.core.data.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "account")
@Getter
@Setter
public class AccountEntity {
    @Id
    private String id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "open_date")
    private LocalDate openDate;
    @Column(name = "close_date")
    @Nullable
    private LocalDate closeDate;
    private int balance;
}

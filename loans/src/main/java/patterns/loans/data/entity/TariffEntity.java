package patterns.loans.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tariff")
@Getter
@Setter
public class TariffEntity {
    @Id
    private String id;
    @Column(name = "user_id")
    private String userId;
    private String name;
    private double procent;
}

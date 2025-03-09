package patterns.users.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "client")
@Getter
@Setter
public class ClientEntity extends UserEntity {
    @OneToOne
    private PassportEntity passport;
}

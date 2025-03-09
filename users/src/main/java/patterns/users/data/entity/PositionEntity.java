package patterns.users.data.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "position")
@Getter
@Setter
public class PositionEntity {
    @Id
    private String id;
    private String name;
    @Nullable
    private String description;
}

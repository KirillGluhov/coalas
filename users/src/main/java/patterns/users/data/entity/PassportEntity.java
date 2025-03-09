package patterns.users.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "passport")
@Getter
@Setter
public class PassportEntity {
    @Id
    private String id;
    private String series;
    private String number;
    @Column(name = "issue_date")
    private LocalDate issueDate;
    @Column(name = "department_code")
    private String DepartmentCode;
    @Column(name = "department_name")
    private String DepartmentName;
}

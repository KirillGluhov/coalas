package patterns.users.data.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import patterns.users.data.enums.Gender;
import patterns.users.data.enums.Role;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
@Getter
@Setter
public class UserEntity {
    @Id
    private String id;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "second_name")
    @Nullable
    private String secondName;
    private Gender gender;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "is_blicked")
    private boolean isBlocked;
    private String password;
    private Role role;
    private String email;
    private String phone;
}
package patterns.users.data.dto.response;

import lombok.Getter;
import lombok.Setter;
import patterns.users.data.enums.Gender;

import java.time.LocalDate;

@Getter
@Setter
public class UserInfoDto {
    private String id;
    private String name;
    private String lastName;
    private String secondName;
    private Gender gender;
    private LocalDate birthDate;
    private boolean isBlocked;
    private String email;
    private String phone;
    private String passportId;
    private String positionId;
}
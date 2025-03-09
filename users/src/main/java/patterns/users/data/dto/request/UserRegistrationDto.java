package patterns.users.data.dto.request;

import lombok.Getter;
import lombok.Setter;
import patterns.users.data.enums.Role;

@Getter
@Setter
public class UserRegistrationDto {
    private String name;
    private String lastName;
    private String secondName;
    private String gender;
    private String birthDate;
    private String phone;
    private String email;
    private String password;
    private Role role;
    private String positionId;
    private PassportDto passport;
}

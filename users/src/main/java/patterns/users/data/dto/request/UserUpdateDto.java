package patterns.users.data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDto {
    private String name;
    private String lastName;
    private String secondName;
    private String gender;
    private String birthDate;
    private String phone;
    private String email;
    private String positionId;
}

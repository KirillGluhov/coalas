package patterns.users.data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    private String phone;
    private String email;
    private String password;
}
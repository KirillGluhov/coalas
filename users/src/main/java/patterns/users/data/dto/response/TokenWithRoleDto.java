package patterns.users.data.dto.response;

import lombok.Getter;
import lombok.Setter;
import patterns.users.data.enums.Role;

@Getter
@Setter
public class TokenWithRoleDto {
    private String accessToken;
    private String refreshToken;
    private Role userType;
}

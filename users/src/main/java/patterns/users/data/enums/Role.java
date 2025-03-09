package patterns.users.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum Role {
    CLIENT("client"),
    EMPLOYEE("employee");

    Role(String value) {
        this.value = value;
    }

    private final String value;
}

package patterns.users.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    Gender(String value) {
        this.value = value;
    }

    private final String value;
}

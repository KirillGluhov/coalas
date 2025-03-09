package patterns.loans.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum Status {
    OPEN("open"),
    CLOSE("close");

    Status(String value) {
        this.value = value;
    }

    private final String value;
}

package patterns.core.data.enums;

import lombok.Getter;

@Getter
public enum Status {
    OPEN("Open"),
    CLOSE("Close");

    Status(String value) {
        this.value = value;
    }

    private final String value;
}

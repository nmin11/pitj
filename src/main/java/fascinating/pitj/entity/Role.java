package fascinating.pitj.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    GENERAL("ROLE_GENERAL");

    private String value;
}

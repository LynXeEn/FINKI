package mk.ukim.finki.wp.jan2022.g1.model;

import org.springframework.security.core.GrantedAuthority;

public enum TaskCategory implements GrantedAuthority {
    FEATURE,
    BUG,
    OTHER;

    @Override
    public String getAuthority() {
        return name();
    }
}

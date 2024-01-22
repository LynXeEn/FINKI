package mk.ukim.finki.wp.september2021.model;

import org.springframework.security.core.GrantedAuthority;

public enum NewsType implements GrantedAuthority {
    DRAFT,
    PUBLIC,
    PROMOTION;

    @Override
    public String getAuthority() {
        return name();
    }
}

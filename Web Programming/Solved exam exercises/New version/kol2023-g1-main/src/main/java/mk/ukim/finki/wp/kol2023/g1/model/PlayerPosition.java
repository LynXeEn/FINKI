package mk.ukim.finki.wp.kol2023.g1.model;

import org.springframework.security.core.GrantedAuthority;

public enum PlayerPosition implements GrantedAuthority {
    G,
    C,
    F;

    @Override
    public String getAuthority() {
        return name();
    }
}

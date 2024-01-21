package mk.ukim.finki.wp.june2022.g1.model;

import org.springframework.security.core.GrantedAuthority;

public enum OSType implements GrantedAuthority {
    WINDOWS,
    DEBIAN,
    UBUNTU;

    @Override
    public String getAuthority() {
        return name();
    }
}

package mk.ukim.finki.wp.jan2022.g2.model;

import org.springframework.security.core.GrantedAuthority;

public enum DiscussionTag implements GrantedAuthority {
    IT,
    FINANCE,
    OTHER;

    @Override
    public String getAuthority() {
        return name();
    }
}

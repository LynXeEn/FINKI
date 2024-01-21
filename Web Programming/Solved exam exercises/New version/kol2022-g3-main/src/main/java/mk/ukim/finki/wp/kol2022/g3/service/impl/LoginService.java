package mk.ukim.finki.wp.kol2022.g3.service.impl;

import mk.ukim.finki.wp.kol2022.g3.model.ForumUser;
import mk.ukim.finki.wp.kol2022.g3.model.exceptions.InvalidForumUserIdException;
import mk.ukim.finki.wp.kol2022.g3.repository.ForumUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class LoginService implements UserDetailsService {

    private final ForumUserRepository forumUserRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginService(ForumUserRepository forumUserRepository, PasswordEncoder passwordEncoder) {
        this.forumUserRepository = forumUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ForumUser user = forumUserRepository.findForumUserByEmail(username);
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),  // <-- Return the raw (non-encoded) password here
                Stream.of(new SimpleGrantedAuthority("ROLE_" + user.getType())).collect(Collectors.toList())
        );
    }

}

package mk.ukim.finki.wp.exam.example.service.impl;

import mk.ukim.finki.wp.exam.example.model.Role;
import mk.ukim.finki.wp.exam.example.model.User;
import mk.ukim.finki.wp.exam.example.model.exceptions.InvalidUsernameException;
import mk.ukim.finki.wp.exam.example.repository.UserRepository;
import mk.ukim.finki.wp.exam.example.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User create(String username, String password, Role role) {
        return userRepository.save(new User(username, passwordEncoder.encode(password), role));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(InvalidUsernameException::new);
        return new org.springframework.security.core.userdetails.User(
          user.getUsername(),
          user.getPassword(),
          Collections.singletonList(user.getRole())
        );
    }
}

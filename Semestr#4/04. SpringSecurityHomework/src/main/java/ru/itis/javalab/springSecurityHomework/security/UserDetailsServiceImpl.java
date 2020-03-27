package ru.itis.javalab.springSecurityHomework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.javalab.springSecurityHomework.model.User;
import ru.itis.javalab.springSecurityHomework.repository.UserRepository;


import java.util.Optional;

@Component(value = "MyUserDetails")
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) {
        Optional<User> optionUser = userRepository.findByEmail(s);
        if (optionUser.isPresent()) {
            return new UserDetailsImpl(optionUser.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}

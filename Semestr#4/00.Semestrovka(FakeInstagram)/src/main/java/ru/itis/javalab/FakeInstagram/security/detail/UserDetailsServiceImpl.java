package ru.itis.javalab.FakeInstagram.security.detail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.repository.interfaces.UserRepository;


import java.util.Optional;

@Component(value = "MyUserDetails")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<User> optionUser = userRepository.findByEmail(email);
        if (optionUser.isPresent()) {
            User  user = optionUser.get();
            System.out.println(user.toString());
            return new UserDetailsImpl(optionUser.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}

package ru.itis.javalab.springSecurityHomework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.itis.javalab.springSecurityHomework.dto.UserDto;
import ru.itis.javalab.springSecurityHomework.model.User;
import ru.itis.javalab.springSecurityHomework.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto login(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto signUp(UserDto userDto) {
        User user = User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
        userRepository.save(user);
        userDto.setId(user.getId());

        return userDto;
    }
}
package ru.itis.javalab.springSecurityHomework.service;

import org.springframework.stereotype.Service;
import ru.itis.javalab.springSecurityHomework.dto.UserDto;

@Service
public interface UserService {
    UserDto login(UserDto userDto);

    UserDto signUp(UserDto userDto);
}

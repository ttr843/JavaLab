package ru.itis.javalab.websockethomework.service;

import ru.itis.javalab.websockethomework.dto.UserDto;

import java.util.Optional;

public interface UserService {
    void signUp(UserDto userDto);

    void signIn(UserDto userDto);

    Optional<UserDto> verify(String token);

    Optional<UserDto> findUserById(Long id);
}

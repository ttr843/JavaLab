package ru.javalab.servletshop.service;

import ru.javalab.servletshop.dto.UserDto;

public interface SignUpService {
    UserDto signUp(String login,String password);
}

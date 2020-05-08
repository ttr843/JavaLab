package ru.itis.javalab.FakeInstagram.restServices.interfaces;


import ru.itis.javalab.FakeInstagram.dto.SignInDto;
import ru.itis.javalab.FakeInstagram.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInData);
}

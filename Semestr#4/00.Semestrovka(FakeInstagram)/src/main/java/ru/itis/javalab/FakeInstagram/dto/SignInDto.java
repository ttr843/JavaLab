package ru.itis.javalab.FakeInstagram.dto;

import lombok.Data;

@Data
public class SignInDto {
    private String email;
    private String password;
}

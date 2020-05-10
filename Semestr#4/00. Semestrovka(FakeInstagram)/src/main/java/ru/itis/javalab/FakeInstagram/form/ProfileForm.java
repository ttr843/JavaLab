package ru.itis.javalab.FakeInstagram.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProfileForm {
    @NotNull(message = "name is empty")
    @NotBlank(message = "empty")
    private String name;

    @NotNull(message = "surname is empty")
    @NotBlank(message = "empty")
    private String surname;

    @Email(message = "incorrect email")
    @NotBlank(message = "empty")
    private String email;

    @NotNull(message = "password is empty")
    @NotBlank(message = "empty")
    private String password;

}

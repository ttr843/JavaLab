package ru.itis.javalab.FakeInstagram.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class profileForm {
    private String name;


    private String surname;

    @Email
    @NotNull
    private String email;

    private String password;

}

package ru.itis.javalab.FakeInstagram.form;

import lombok.Data;

import javax.validation.GroupSequence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class profileForm {
    private String name;


    private String surname;

    @Email(message = "{error.email.email}")
    private String email;

    private String password;

}

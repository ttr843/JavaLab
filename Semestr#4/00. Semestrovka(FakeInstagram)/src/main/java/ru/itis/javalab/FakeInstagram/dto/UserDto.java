package ru.itis.javalab.FakeInstagram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;
    @NotNull(message = "name is empty")
    private String name;
    @NotNull(message = "surname is empty")
    private String surname;
    @Email(message = "Wrong mail")
    @NotNull(message = "email is empty")
    private String email;
    @NotNull(message = "password is empty")
    private String password;

    private String photo;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

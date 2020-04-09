package ru.itis.javalab.FakeInstagram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String photo;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

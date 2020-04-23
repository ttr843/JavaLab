package ru.itis.javalab.websockethomework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private Long id;
    private String name;
    private String email;
    private String passwordHash;
}

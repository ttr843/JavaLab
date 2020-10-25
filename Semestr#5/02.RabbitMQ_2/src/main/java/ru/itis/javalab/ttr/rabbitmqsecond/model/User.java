package ru.itis.javalab.ttr.rabbitmqsecond.model;


import lombok.*;
import lombok.extern.slf4j.Slf4j;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private long passportID;
}
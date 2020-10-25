package ru.itis.javalab.generatepdf.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 18.09.2020
 * User
 *
 * @author Ruslan Pashin
 * github ttr843
 * @version v1.0
 */

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
    private int age;
    private String date;
}

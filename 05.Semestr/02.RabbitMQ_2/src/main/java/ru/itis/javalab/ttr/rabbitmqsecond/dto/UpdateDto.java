package ru.itis.javalab.ttr.rabbitmqsecond.dto;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
@Getter
@Setter
public class UpdateDto {
    private long passportID;
    private long newPassportID;
}

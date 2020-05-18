package ru.itis.messagequeue.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //конвертирует JSON в объекты и обратно
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}

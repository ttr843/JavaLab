package ru.itis.messagequeue.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message {
    private String command;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String messageId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> body;

}

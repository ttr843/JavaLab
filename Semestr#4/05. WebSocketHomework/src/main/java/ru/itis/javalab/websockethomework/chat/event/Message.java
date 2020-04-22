package ru.itis.javalab.websockethomework.chat.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Message {
    private String text;
    private String roomId;
    private String name;
}

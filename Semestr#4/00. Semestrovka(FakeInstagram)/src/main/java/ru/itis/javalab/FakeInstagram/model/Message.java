package ru.itis.javalab.FakeInstagram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.FakeInstagram.dto.MessageDto;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Message {
    private long id;
    private String pageId;
    private String text;
    private long userId;

    public MessageDto transformToMessageDto() {
        MessageDto messageDto = MessageDto.builder()
                .userId(String.valueOf(userId))
                .pageId(pageId)
                .text(text)
                .build();
        return messageDto;
    }
}

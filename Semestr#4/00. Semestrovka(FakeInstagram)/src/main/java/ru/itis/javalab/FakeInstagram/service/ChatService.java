package ru.itis.javalab.FakeInstagram.service;

import ru.itis.javalab.FakeInstagram.dto.MessageDto;

import java.util.List;

public interface ChatService {
    List<MessageDto> takeAllMessages();

    void addMessage(MessageDto messageDto);

    void updateStatusOfMessage(long id);

    List<String> getAllPageIds();
}

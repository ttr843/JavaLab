package ru.itis.javalab.FakeInstagram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.FakeInstagram.dto.MessageDto;
import ru.itis.javalab.FakeInstagram.model.Message;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.repository.MessageRepository;
import ru.itis.javalab.FakeInstagram.repository.UserRepository;

import java.util.*;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<MessageDto> takeAllMessages() {
        return messageRepository.findAllMessages();
    }

    @Override
    public void addMessage(MessageDto messageDto) {
        Message message = Message.builder()
                .pageId(messageDto.getPageId())
                .text(messageDto.getText())
                .userId(Integer.parseInt(messageDto.getUserId()))
                .build();
        messageRepository.save(message);
    }

    @Override
    public void updateStatusOfMessage(long id) {
        messageRepository.updateStatusOfMessage(id);
    }

    @Override
    public List<String> getAllPageIds() {
        return messageRepository.getAllPageIds();
    }


}

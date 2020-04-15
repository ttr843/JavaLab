package ru.itis.javalab.FakeInstagram.repository;

import ru.itis.javalab.FakeInstagram.dto.MessageDto;
import ru.itis.javalab.FakeInstagram.model.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Long, Message> {
    List<MessageDto> findAllMessages();

    void updateStatusOfMessage(Long id);

    List<String> getAllPageIds();
}

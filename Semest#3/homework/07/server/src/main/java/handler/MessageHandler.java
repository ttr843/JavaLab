package handler;

import Dto.MessageDto;
import context.Component;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import model.Command;
import model.Message;
import payload.JsonObject;
import payload.MessagePayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import repository.MessageRepository;
import repository.MessageRepositoryJdbcImpl;

import java.sql.Connection;

@NoArgsConstructor
public class MessageHandler implements Handler, Component {
    private MessageRepository messageRepository;

    public MessageDto handle (MessagePayload messagePayload) {
       messageRepository.save(messagePayload.getMessage());
        return MessageDto.from(messagePayload.getMessage());
    }
}

package handler;

import Dto.MessagesDto;
import context.Component;
import lombok.NoArgsConstructor;
import payload.MessagesPayload;
import payload.GetMessagesCommandPayload;
import payload.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import model.Message;
import service.MessageService;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;
@NoArgsConstructor
public class GetMessagesCommandHandler implements Handler, Component {
    private MessageService messageService;
    public MessagesDto handle (GetMessagesCommandPayload getMessagesCommandPayload) {
        int page = getMessagesCommandPayload.getGetMessagesCommand().getPage();
        int size = getMessagesCommandPayload.getGetMessagesCommand().getSize();
        List<Message> messages =messageService.findByPage(page, size);
        return MessagesDto.from(messages);
    }
}

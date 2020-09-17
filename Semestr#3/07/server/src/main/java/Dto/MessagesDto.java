package Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Message;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessagesDto implements ForOneDto{

    private List<Message> messages;

    public static MessagesDto from(List<Message> messages) {
        return new MessagesDto(messages);
    }

}

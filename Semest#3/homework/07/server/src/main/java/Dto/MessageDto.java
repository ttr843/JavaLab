package Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Message;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MessageDto implements ForAllDto{
    private Message message;


    public static MessageDto from (Message message) {
        return new MessageDto(message);
    }
}

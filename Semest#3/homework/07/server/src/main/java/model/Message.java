package model;

import Dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private UserDto sender;
    private String text;
    private Date timesent;

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", text='" + text + '\'' +
                ", timesent=" + timesent +
                '}';
    }
}
package model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Message {

    private User sender;
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
package ru.itis.messagequeue.models.jpa;

import lombok.*;
import ru.itis.messagequeue.models.Status;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "queue")
public class MessageEntity{
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String body;

    @ManyToOne
    private MyQueue queue;

    private Date date;


}

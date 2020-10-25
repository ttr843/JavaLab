package ru.itis.messagequeue.models.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.messagequeue.models.jpa.MessageEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class MyQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String queueName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "queue", orphanRemoval = true)
    private List<MessageEntity> messages = new ArrayList<>();
}

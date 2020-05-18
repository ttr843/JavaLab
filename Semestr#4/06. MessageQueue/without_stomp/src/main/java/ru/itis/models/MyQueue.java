package ru.itis.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MyQueue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String queueName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "myQueue")
    private List<Task> tasks = new ArrayList<>();

    @OneToOne
    private JlmqConsumer jlmqConsumer;
}

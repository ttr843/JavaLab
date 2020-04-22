package ru.itis.javalab.websockethomework.chat.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.javalab.websockethomework.chat.Client;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Room {
    private String id;
    private List<Client> clients;
}

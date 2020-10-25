package ru.itis.messagequeue.websocket;

import ru.itis.messagequeue.models.Message;

public interface MessageHandler {
    void onReceive(Message message);
}

package ru.javalab.socketapp.protocol;

import ru.javalab.socketapp.database.model.Message;

import java.util.ArrayList;

public class PaginationResponse {
    private ArrayList<Message> messages;

    public PaginationResponse() {

    }

    public PaginationResponse(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }
}

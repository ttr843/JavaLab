package ru.javalab.socketapp.protocol;

public class MessagePayload {
    private String message;

    public MessagePayload(){

    }

    public MessagePayload(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}

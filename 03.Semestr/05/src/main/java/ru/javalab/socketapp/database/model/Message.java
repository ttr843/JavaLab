package ru.javalab.socketapp.database.model;

public class Message {
    private int id,idUser;
    private String text,date;

    public Message(int id, int idUser, String text, String date) {
        this.id = id;
        this.idUser = idUser;
        this.text = text;
        this.date = date;
    }

    public Message(int idUser, String text) {
        this.idUser = idUser;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }
}

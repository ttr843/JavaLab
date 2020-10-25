package ru.itis.javalab.ttr.rabbitmqsecond.service;

import ru.itis.javalab.ttr.rabbitmqsecond.model.User;

public interface DocumentService<E> {
    public void CreateDocument(User user);
    public void addContent(E document,User user);
}

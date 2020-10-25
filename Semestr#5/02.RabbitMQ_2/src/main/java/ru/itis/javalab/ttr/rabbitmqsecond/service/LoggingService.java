package ru.itis.javalab.ttr.rabbitmqsecond.service;

import ru.itis.javalab.ttr.rabbitmqsecond.model.User;

import java.io.File;

public interface LoggingService {
    public File createTxt();
    public void addData(File file, User user);
    public void addData(File file, String string);
}

package ru.itis.javalab.fileload.services;

public interface EmailService {
    void sendMail(String subject, String text, String email);
}

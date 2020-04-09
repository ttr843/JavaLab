package ru.itis.javalab.FakeInstagram.service;

public interface EmailService {
    void sendMail(String subject, String html, String mail);
}

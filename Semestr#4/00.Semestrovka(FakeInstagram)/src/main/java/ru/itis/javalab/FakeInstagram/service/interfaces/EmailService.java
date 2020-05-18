package ru.itis.javalab.FakeInstagram.service.interfaces;

public interface EmailService {
    void sendMail(String subject, String html, String mail);
}

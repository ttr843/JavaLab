package ru.itis.javalab.websockethomework.util;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class PasswordEncoder {

    public String hashPassword(String str) {
        String hash = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(str.getBytes(StandardCharsets.UTF_8));
            hash = bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    private String bytesToHex(byte[] hashArr) {
        StringBuilder hexString = new StringBuilder();
        for (byte hash : hashArr) {
            String hex = Integer.toHexString(0xff & hash);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public boolean checkPassword(String password, String hash) {
        return hashPassword(password).equals(hash);
    }
}
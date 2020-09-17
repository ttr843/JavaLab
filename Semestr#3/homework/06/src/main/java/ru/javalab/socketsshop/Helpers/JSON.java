package ru.javalab.socketsshop.Helpers;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;

public class JSON {
    private static String SECRET_KEY = "secret";

    public static String makeJsonFormat(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    public static Algorithm getAlgorithm() {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return  algorithm;
        } catch (UnsupportedEncodingException e) {
            throw  new IllegalStateException(e);
        }
    }
}

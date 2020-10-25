package ru.itis.javalab.generatepdf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.javalab.generatepdf.model.User;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * 18.09.2020
 * Producer
 *
 * @author Ruslan Pashin
 * github ttr843
 * @version v1.0
 */
public class Producer {
    private static final String EXCHANGE_NAME = "documents";
    private static final String EXCHANGE_TYPE = "fanout";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            Scanner scanner = new Scanner(System.in);
            User user = User.builder().build();
            System.out.println("FirstName :");
            user.setFirstName(scanner.nextLine());
            System.out.println("LastName :");
            user.setLastName(scanner.nextLine());
            System.out.println("PassportID :");
            user.setPassportID(scanner.nextLong());
            System.out.println("Age :");
            user.setAge(scanner.nextInt());
            System.out.println("Date :");
            user.setDate(scanner.nextLine());
            ObjectMapper objectMapper = new ObjectMapper();
            channel.basicPublish(EXCHANGE_NAME, "",
                    null, objectMapper.writeValueAsString(user).getBytes());
        } catch (IOException |
                TimeoutException e) {
            System.out.println("something bad...");
            throw new IllegalArgumentException(e);
        }
    }
}

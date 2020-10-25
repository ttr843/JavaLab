package ru.itis.javalab.ttr.rabbitmqsecond;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.javalab.ttr.rabbitmqsecond.dto.UpdateDto;
import ru.itis.javalab.ttr.rabbitmqsecond.model.User;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Main {

    private static final String FANOUT_EXCHANGE = "user";
    private static final String FANOUT_TYPE = "fanout";

    private final static String PDF_ROUTING_KEY = "pdf";
    private final static String WORD_ROUTING_KEY = "word";
    private final static String PDF_QUEUE = "document_pdf_queue";
    private final static String WORD_QUEUE = "document_word_queue";
    private final static String DIRECT_EXCHANGE = "document_direct_exchange";
    private final static String DIRECT_TYPE = "direct";

    private final static String TOPIC_EXCHANGE = "topic_exchange";
    private final static String TOPIC_TYPE = "topic";
    private final static String ACCOUNT_DELETE_ROUTING_KEY = "account.delete";
    private final static String ACCOUNT_UPDATE_PASS_ID_ROUTING_KEY = "account.update";


    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("please select what do u want : 1.Get Document, 2. Use Function ");
        Scanner scanner = new Scanner(System.in);
        int typeOfOperation = scanner.nextInt();
        switch (typeOfOperation) {
            case (1) :
                try {
                    Connection connection = connectionFactory.newConnection();
                    Channel channel = connection.createChannel();
                    channel.exchangeDeclare(FANOUT_EXCHANGE, FANOUT_TYPE);
                    channel.exchangeDeclare(DIRECT_EXCHANGE, DIRECT_TYPE);
                    channel.queueBind(PDF_QUEUE, DIRECT_EXCHANGE, PDF_ROUTING_KEY);
                    channel.queueBind(WORD_QUEUE, DIRECT_EXCHANGE, WORD_ROUTING_KEY);
                    User user = inputUser();
                    System.out.println("which type of document ?\n1.pdf\n2.word");
                    String routingKey;
                    if (scanner.nextInt() == 1) {
                        routingKey = PDF_ROUTING_KEY;
                    } else {
                        routingKey = WORD_ROUTING_KEY;
                    }
                    channel.basicPublish(FANOUT_EXCHANGE, "",
                            null, objectMapper.writeValueAsString(user).getBytes());
                    channel.basicPublish(DIRECT_EXCHANGE, routingKey, null,
                            objectMapper.writeValueAsString(user).getBytes());
                } catch (IOException |
                        TimeoutException e) {
                    System.err.println("something bad...");
                    throw new IllegalArgumentException(e);
                }
                break;
            case (2) :
                try {
                    Connection connection = connectionFactory.newConnection();
                    Channel channel = connection.createChannel();
                    channel.exchangeDeclare(TOPIC_EXCHANGE,TOPIC_TYPE);
                    User user = inputUser();
                    System.out.println("which function you want to use?\n1.delete\n2.update");
                    int i = scanner.nextInt();
                    String currentRouting;
                    switch (i){
                        case 1 :
                            currentRouting = ACCOUNT_DELETE_ROUTING_KEY;
                            channel.basicPublish(TOPIC_EXCHANGE, currentRouting, null,
                                    objectMapper.writeValueAsString(user).getBytes());
                            break;
                        case 2 :
                            currentRouting = ACCOUNT_UPDATE_PASS_ID_ROUTING_KEY;
                            System.out.println("write new passportId");
                            long newPassID = scanner.nextLong();
                            UpdateDto updateDto = UpdateDto.builder()
                                    .passportID(user.getPassportID())
                                    .newPassportID(newPassID)
                                    .build();
                            channel.basicPublish(TOPIC_EXCHANGE, currentRouting, null,
                                    objectMapper.writeValueAsString(updateDto).getBytes());
                            break;
                    }
                } catch (IOException | TimeoutException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    public static User inputUser(){
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        System.out.println("FirstName :");
        user.setFirstName(scanner.next());
        System.out.println("LastName :");
        user.setLastName(scanner.next());
        System.out.println("PassportID :");
        user.setPassportID(scanner.nextLong());
        return user;
    }
}

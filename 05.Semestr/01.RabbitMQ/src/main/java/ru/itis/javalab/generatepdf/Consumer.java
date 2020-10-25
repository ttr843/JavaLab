package ru.itis.javalab.generatepdf;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ru.itis.javalab.generatepdf.model.User;
import ru.itis.javalab.generatepdf.service.DocumentService;
import ru.itis.javalab.generatepdf.service.DocumentServiceImpl;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * 18.09.2020
 * Consumer
 *
 * @author Ruslan Pashin
 * github ttr843
 * @version v1.0
 */
public class Consumer {
    private static final String EXCHANGE_NAME = "documents";
    private static final String EXCHANGE_TYPE = "fanout";
    private static final String DISMISSAL_DOC = "DISMISSAL";
    private static final String DEDUCTION_DOC = "DEDUCTION";
    private static final String ACADEMIC_VACATION_DOC = "ACADEMIC VACATION";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1.dismissal,2.deduction,3.academic vacation,please write number which document u want: ");
        int numberDocument = scanner.nextInt();
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, EXCHANGE_NAME, "");
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    User user = objectMapper.readValue(new String(message.getBody()), User.class);
                    DocumentService documentService = new DocumentServiceImpl();
                    switch (numberDocument) {
                        case 1:
                            documentService.createDocument(DISMISSAL_DOC, user);
                            break;
                        case 2:
                            documentService.createDocument(DEDUCTION_DOC, user);
                            break;
                        case 3:
                            documentService.createDocument(ACADEMIC_VACATION_DOC, user);
                            break;
                    }
                    System.out.println("Document created");
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (JsonProcessingException e) {
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            };
            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
            });
        } catch (IOException | TimeoutException e) {
            System.out.println("something bad...");
            throw new IllegalArgumentException(e);
        }
    }
}

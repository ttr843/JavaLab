package ru.itis.javalab.ttr.rabbitmqsecond.consumer.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.javalab.ttr.rabbitmqsecond.service.LoggingService;
import ru.itis.javalab.ttr.rabbitmqsecond.service.LoggingServiceImpl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeoutException;

public class Logger {
    private final static String TOPIC_EXCHANGE = "topic_exchange";
    private final static String ACCOUNT_ROUTING_KEY = "account.*";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        LoggingService loggingService = new LoggingServiceImpl();
        File file = loggingService.createTxt();
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, TOPIC_EXCHANGE, ACCOUNT_ROUTING_KEY);
            channel.basicConsume(queueName, false, (consumerTag, message) -> {
                try {
                    loggingService.addData(file,"use some function at " +
                            new SimpleDateFormat("dd.MM.YYYY").format(System.currentTimeMillis()));
                    System.out.println("logged");
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (IOException e) {
                    System.err.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }

            }, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

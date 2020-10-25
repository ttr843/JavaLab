package ru.itis.javalab.ttr.rabbitmqsecond.consumer.topic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.javalab.ttr.rabbitmqsecond.model.User;
import ru.itis.javalab.ttr.rabbitmqsecond.repository.UserRepository;
import ru.itis.javalab.ttr.rabbitmqsecond.repository.UserRepositoryImpl;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DeleteConsumer {
    private final static String TOPIC_EXCHANGE = "topic_exchange";
    private final static String ACCOUNT_DELETE_ROUTING_KEY = "account.delete";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        ObjectMapper objectMapper = new ObjectMapper();
        UserRepository userRepository = new UserRepositoryImpl();
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, TOPIC_EXCHANGE, ACCOUNT_DELETE_ROUTING_KEY);
            channel.basicConsume(queueName, false, (consumerTag, message) -> {
                try {
                    User user = objectMapper.readValue(new String(message.getBody()), User.class);
                    userRepository.delete(user);
                    System.out.println("deleted " + user.getPassportID());
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

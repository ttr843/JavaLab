package ru.itis.javalab.ttr.rabbitmqsecond.consumer.fanout;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import ru.itis.javalab.ttr.rabbitmqsecond.model.User;
import ru.itis.javalab.ttr.rabbitmqsecond.repository.UserRepository;
import ru.itis.javalab.ttr.rabbitmqsecond.repository.UserRepositoryImpl;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DBConsumer {
    private static final String FANOUT_EXCHANGE = "user";
    private static final String FANOUT_TYPE = "fanout";

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        UserRepository userRepository = new UserRepositoryImpl();
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);
            channel.exchangeDeclare(FANOUT_EXCHANGE,FANOUT_TYPE);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue,FANOUT_EXCHANGE,"");
            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    User user = objectMapper.readValue(new String(message.getBody()), User.class);
                    userRepository.save(user);
                    System.out.println("SAVED user with passportId " + user.getPassportID());
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (JsonProcessingException e) {
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            };
            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {
            });
        }catch (IOException | TimeoutException e){
            System.err.println("something bad..");
            throw new IllegalArgumentException(e);
        }
    }
}

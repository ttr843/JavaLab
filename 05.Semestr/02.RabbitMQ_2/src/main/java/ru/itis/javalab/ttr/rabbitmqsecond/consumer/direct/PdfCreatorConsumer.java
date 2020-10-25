package ru.itis.javalab.ttr.rabbitmqsecond.consumer.direct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Document;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import ru.itis.javalab.ttr.rabbitmqsecond.model.User;
import ru.itis.javalab.ttr.rabbitmqsecond.service.DocumentService;
import ru.itis.javalab.ttr.rabbitmqsecond.service.PDFDocumentServiceImpl;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PdfCreatorConsumer {
    private final static String PDF_QUEUE = "document_pdf_queue";

    public static void main(String[] args) {
        DocumentService<Document> documentService = new PDFDocumentServiceImpl();
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.basicQos(3);
            channel.basicConsume(PDF_QUEUE, false, (consumerTag, message) -> {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    User user = objectMapper.readValue(new String(message.getBody()), User.class);
                    documentService.CreateDocument(user);
                    System.out.println("Pdf document created with passportID " + user.getPassportID());
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

package ru.itis.messagequeue.components;

public class JlmqProducerFactory {

    public static JlmqProducer getEmailProducer(){
        return new JlmqEmailProducerImpl("documents_for_generate");
    }
}

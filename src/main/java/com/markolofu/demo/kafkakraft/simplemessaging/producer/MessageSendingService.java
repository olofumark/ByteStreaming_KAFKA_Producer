package com.markolofu.demo.kafkakraft.simplemessaging.producer;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
 * Service class responsible for sending messages to a Kafka topic.
 * Utilizes KafkaTemplate to send messages to the specified topic.
 * 
 * <p>
 * This service is annotated with {@code @Service} to indicate that it is a Spring-managed
 * component, and {@code @RequiredArgsConstructor} is used to generate a constructor
 * for the final fields.
 * </p>
 * 
 * <p>
 * The {@code sendMessage} method sends a given message to the Kafka topic
 * {@code simple-messaging-topic}.
 * </p>
 * 
 * <p>
 * Dependencies:
 * <ul>
 *   <li>{@code KafkaTemplate<String, String>} - Used for sending messages to Kafka.</li>
 * </ul>
 * </p>
 */
@Service
@RequiredArgsConstructor
public class MessageSendingService {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final String topicName = "simple-messaging-topic";

    public void sendMessage(String message) {

        System.out.println("Sending Message... " + message);

        // send the message
        CompletableFuture<SendResult<String, byte[]>> cf = kafkaTemplate.send(topicName , message.getBytes());
        cf.whenComplete((result, ex) -> {
            if (ex != null) {
                System.err.println("Error sending message: " + ex.getMessage());
            } else {
                System.out.println("Message sent successfully: " + result.getProducerRecord().value());
            }
        });
        // if(cf.isDone()){
        //     System.out.println("Message sent successfully: " + message);
        // }
    }
    
}

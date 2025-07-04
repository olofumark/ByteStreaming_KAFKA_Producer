package com.markolofu.demo.kafkakraft.simplemessaging.producer;

import java.util.Random;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @apiNote {@link MessageSender}
*          A class sending messages to a Kafka topic at regular intervals.
 * This class uses a scheduled task to send messages every 5 seconds.
 * It utilizes the {@link MessageSendingService} to handle the actual sending of messages.
 * It is annotated with {@link Component} to allow Spring to manage it as a bean.
 * * @see MessageSendingService
 * @see org.springframework.scheduling.annotation.Scheduled
 */

@Component
public class MessageSender {

    private Random random = new Random();
    private String[] messages = {"Hello Mark,  You are primed for success!", "You Mark is the greatest man to ever live!", 
    "Surely Mark, Your inventions will rock the entire universe!", "Mark, you are destined for greatness!",
     "Mark, you are the best thing that has ever happened to this world!"};

    private MessageSendingService messageSendingService;

    MessageSender(MessageSendingService messageSendingService){
        this.messageSendingService = messageSendingService;
    }
    

    @Scheduled(fixedRate = 5000)
    // @KafkaListener(topics = "simple-messaging-topic", groupId = "simple-messaging-group")
    public void generateMessage(){

        int index = random.nextInt(messages.length);

        String message = messages[index];
        
        messageSendingService.sendMessage(message);
        
    }

}

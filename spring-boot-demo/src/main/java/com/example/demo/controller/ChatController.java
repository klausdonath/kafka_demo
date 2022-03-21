package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Message;

@RestController
public class ChatController {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;
    private final static String TEST_TOPIC = "Testtopic2Partitions";

    @PostMapping(value = "/api/send", consumes = "application/json", produces = "application/json")
    public void sendMessage(@RequestBody Message message) {
        message.setTimestamp(LocalDateTime.now().toString());
        try {
            //Sending the message to kafka topic queue
            kafkaTemplate.send(TEST_TOPIC,message.getSender(),message).get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @MessageMapping("/sendMessage")
    @SendTo("/topic/group")
    public Message broadcastGroupMessage(@Payload Message message) {
        //Sending this message to all the subscribers
        return message;
    }
    
    //Avro
    //ADD Topic
    //Get topics 
    //Security
    //Challenge idea to have kafka consumer as client (browser, mobile
    //Zur Laufzeit mehrere Topics consumen (Topic kommt rein, Consumer lauscht)
    
    
    
    

}

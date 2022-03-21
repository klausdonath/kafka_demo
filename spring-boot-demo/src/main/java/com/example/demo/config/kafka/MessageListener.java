package com.example.demo.config.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.model.Message;

@Component
public class MessageListener {
    @Autowired
    SimpMessagingTemplate template;
    
    private final String groupId = "TEST_GROUP";

    @KafkaListener(
            topics = "Testtopic2Partitions",
            groupId = groupId
    )
    public void listen(Message message) {
        System.out.println("sending via kafka listener..");
        System.out.println(message.toString());
        
        
        //TODO db call
        //template.convertAndSend("/topic/group", message);
    }
}



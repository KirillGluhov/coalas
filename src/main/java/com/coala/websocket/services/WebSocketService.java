package com.coala.websocket.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;

    }

    public void sendUpdate(String message, String destination) {
        System.out.println("Destination: " + destination + " Message: " + message);
        messagingTemplate.convertAndSend(destination, message);
    }

}

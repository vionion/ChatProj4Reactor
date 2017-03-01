package com.tsybulko.chat.web.controller.chat;

import com.tsybulko.chat.web.domain.ChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by vtsybulko on 25/02/17.
 */
@Service
public class NewMessageNotifier {
    protected final Logger log = LoggerFactory.getLogger(NewMessageNotifier.class);

    @Autowired
    private SimpMessagingTemplate webSocket;

    @Async
    public void pushChangesToWebSocket(ChatMessage chatMessage) {
        log.info("New message: {}", chatMessage.getMessage());
        webSocket.convertAndSend("/topic/messages/" + chatMessage.getRoom(), chatMessage);
    }

}

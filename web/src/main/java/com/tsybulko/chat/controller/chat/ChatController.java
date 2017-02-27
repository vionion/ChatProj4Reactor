package com.tsybulko.chat.controller.chat;

import com.tsybulko.chat.domain.ChatMessage;
import com.tsybulko.chat.service.message.MessagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by V.Tsybulko on 25.02.2017.
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    protected final Logger log = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private MessagesService messagesService;
    @Autowired
    private NewMessageNotifier messageNotifier;

    @RequestMapping(method = RequestMethod.POST)
    public ChatMessage postMessage(@RequestBody ChatMessage chatMessage) {
        chatMessage.setSentTime(LocalDateTime.now());
        messagesService.saveMessage(chatMessage);

        log.info("Insert {}", chatMessage);
        messageNotifier.pushChangesToWebSocket(chatMessage);
        return chatMessage;
    }

    @RequestMapping(path = "/username", method = RequestMethod.GET)
    public String getUserName(Principal principal) {
        return principal.getName();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ChatMessage> getMessages() {
        //TODO: limit that list
        List<ChatMessage> messages = messagesService.findAllMessages();
        return messages;
    }
}
package com.tsybulko.chat.web.controller.chat;

import com.tsybulko.chat.web.domain.ChatMessage;
import com.tsybulko.chat.web.service.message.MessagesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by vtsybulko on 25/02/17.
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

    @RequestMapping(path = "/room", method = RequestMethod.GET)
    public List<String> getRooms() {
        // TODO: get them from DB
        List<String> rooms = new LinkedList<String>();
        rooms.add("general");
        rooms.add("oval room");
        rooms.add("sauna");
        rooms.add("kitchen");
        return rooms;
    }

    @RequestMapping(path = "/{room}", method = RequestMethod.GET)
    public List<ChatMessage> getMessages(@PathVariable String room) {
        //TODO: limit that list
        List<ChatMessage> chatMessages = messagesService.findAllMessagesByRoom(room);
        return chatMessages;
    }
}
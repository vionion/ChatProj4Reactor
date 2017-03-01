package com.tsybulko.chat.web.service.message;

import com.tsybulko.chat.web.domain.ChatMessage;

import java.util.Collection;
import java.util.List;

/**
 * Created by vtsybulko on 25/02/17.
 */

public interface MessagesService {

    ChatMessage findById(long id);

    List<ChatMessage> findAllMessages();

    void saveMessage(ChatMessage message);

    void saveOrUpdateMessage(ChatMessage message);

    void saveAllMessages(Collection<ChatMessage> messages);

    ChatMessage updateMessage(ChatMessage message);

    void deleteMessageById(long id);

    void deleteAllMessages();

    List<ChatMessage> findAllMessagesByRoom(String room);
}

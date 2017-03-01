package com.tsybulko.chat.web.dao.message;


import com.tsybulko.chat.web.domain.ChatMessage;

import java.util.List;

/**
 * Created by vtsybulko on 25/02/17.
 */

public interface MessagesDao {

    ChatMessage findById(long id);

    void saveMessage(ChatMessage message);

    void saveOrUpdateMessage(ChatMessage message);

    void deleteMessageById(long id);

    void deleteAllMessages();

    List<ChatMessage> findAllMessages();

    List<ChatMessage> findAllMessagesByRoom(String room);

}
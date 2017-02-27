package com.tsybulko.chat.dao.message;

import com.tsybulko.chat.domain.ChatMessage;

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

}
package com.tsybulko.chat.service.message.impl;

import com.tsybulko.chat.dao.message.MessagesDao;
import com.tsybulko.chat.domain.ChatMessage;
import com.tsybulko.chat.service.message.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * Created by vtsybulko on 25/02/17.
 */

@Service
@Transactional
public class MessagesServiceImpl implements MessagesService {

    @Autowired
    private MessagesDao dao;

    @Override
    public List<ChatMessage> findAllMessages() {
        return dao.findAllMessages();
    }


    @Override
    public ChatMessage findById(long id) {
        return dao.findById(id);
    }

    @Override
    public void saveMessage(ChatMessage message) {
        dao.saveMessage(message);
    }

    @Override
    public void saveOrUpdateMessage(ChatMessage message) {
        dao.saveOrUpdateMessage(message);
    }

    @Override
    public void saveAllMessages(Collection<ChatMessage> messages) {
        messages.forEach(this::saveMessage);
    }

    /*
     * It will be an atomic change, 'cause Transactional annotation is used
     */
    @Override
    public ChatMessage updateMessage(ChatMessage message) {
        ChatMessage entity = dao.findById(message.getMessageId());
        if (entity != null) {
            entity.setSentTime(message.getSentTime());
            entity.setMessage(message.getMessage());
            entity.setSender(message.getSender());
            saveMessage(entity);
        }
        return entity;
    }

    @Override
    public void deleteMessageById(long id) {
        dao.deleteMessageById(id);
    }

    @Override
    public void deleteAllMessages() {
        dao.deleteAllMessages();
    }
}

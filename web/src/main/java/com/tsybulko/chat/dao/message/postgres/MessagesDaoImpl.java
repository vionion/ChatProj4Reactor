package com.tsybulko.chat.dao.message.postgres;

import com.tsybulko.chat.dao.AbstractDao;
import com.tsybulko.chat.dao.message.MessagesDao;
import com.tsybulko.chat.domain.ChatMessage;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vtsybulko on 25/02/17.
 */

@Repository("messagesDao")
public class MessagesDaoImpl extends AbstractDao<Long, ChatMessage> implements MessagesDao {

    @Override
    public ChatMessage findById(long id) {
        return getByKey(id);
    }

    @Override
    public void saveMessage(ChatMessage message) {
        persist(message);
    }

    @Override
    public void saveOrUpdateMessage(ChatMessage message) {
        saveOrUpdate(message);
    }

    @Override
    public void deleteMessageById(long id) {
        Query query = getSession().createSQLQuery("delete from messages where message_id = :id");
        query.setLong("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteAllMessages() {
        Query query = getSession().createSQLQuery("delete from messages");
        query.executeUpdate();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ChatMessage> findAllMessages() {
        Criteria criteria = createEntityCriteria();
        return (List<ChatMessage>) criteria.list();
    }

}
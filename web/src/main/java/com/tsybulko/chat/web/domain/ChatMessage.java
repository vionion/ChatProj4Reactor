package com.tsybulko.chat.web.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by vtsybulko on 25/02/17.
 */
@Entity
@Table(name = "messages")
public class ChatMessage {

    @Id
    @Column(name = "message_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("messageId")
    private long messageId;

    //TODO: use ref to user entity here, not naive username
    @Size(max = 100)
    @Column(name = "sender")
    private String sender;

    //TODO: use real entity here, not just String
    @Size(max = 100)
    @Column(name = "room")
    private String room;

    @Size(max = 2500)
    @Column(name = "message")
    private String message;

    @Column(name = "sent_time", nullable = false)
    private LocalDateTime sentTime;

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }

    // Special getters and setters

    @JsonProperty("sentTime")
    public long getSentTimeUtc() {
        return sentTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @JsonProperty("creation")
    public void setSentTimeUtc(long startUtc) {
        sentTime = Instant.ofEpochSecond(startUtc).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @Override
    public String toString() {
        return "ChatMessage [sender=" + sender + ", message=" + message + "]";
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}

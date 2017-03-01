package com.tsybulko.chat.web.domain;

import com.tsybulko.chat.web.exception.TooMuchSwearWordsException;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by vtsybulko on 25/02/17.
 */
public class SessionProfanity {

    private long maxProfanityLevel = Long.MAX_VALUE;

    private AtomicLong profanityLevel = new AtomicLong();

    public SessionProfanity() {
    }

    public SessionProfanity(int maxProfanityLevel) {
        this.maxProfanityLevel = maxProfanityLevel;
    }

    public void increment(long partialProfanity) {
        if (profanityLevel.intValue() + partialProfanity >= maxProfanityLevel) {
            profanityLevel.set(maxProfanityLevel);
            throw new TooMuchSwearWordsException("You reached the max profanity level. You are banned");
        }

        profanityLevel.addAndGet(partialProfanity);
    }
}

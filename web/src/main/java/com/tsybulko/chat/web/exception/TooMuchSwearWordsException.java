package com.tsybulko.chat.web.exception;

/**
 * Created by vtsybulko on 25/02/17.
 */
public class TooMuchSwearWordsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public TooMuchSwearWordsException(String message) {
        super(message);
    }
}

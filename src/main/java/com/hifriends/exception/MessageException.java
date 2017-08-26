package com.hifriends.exception;

/**
 * @author aleksandrprendota on 26.08.17.
 */
public class MessageException extends RuntimeException {

    public MessageException(String message) {
        super(message);
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }

}

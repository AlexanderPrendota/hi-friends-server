package com.hifriends.exception;

/**
 * @author by aleksandrprendota on 26.08.17.
 */
public class PostMessageException extends MessageException {

    public PostMessageException(String message) {
        super(message);
    }

    public PostMessageException(String message, Throwable cause) {
        super(message, cause);
    }

}

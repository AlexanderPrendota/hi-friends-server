package com.hifriends.service.api;

import com.hifriends.model.Chat;

/**
 * @author by aleksandrprendota on 24.08.17.
 */
public interface ChatService {

    Chat findChatMessageBy2users(long ownerId, long userId);

    void createChats(Chat chat, long owner, long recipient);

    Chat findById(long id);

}
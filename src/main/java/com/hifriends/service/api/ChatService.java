package com.hifriends.service.api;

import com.hifriends.model.Chat;

/**
 * @author by aleksandrprendota on 24.08.17.
 */
public interface ChatService {

    Chat findChatById(Long id);

    void save(Chat chat);

    void deleteChat(Chat chat);

}
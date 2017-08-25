package com.hifriends.service.impl;

import com.hifriends.model.Chat;
import com.hifriends.model.User;
import com.hifriends.model.UserChat;
import com.hifriends.repository.ChatRepository;
import com.hifriends.repository.UserChatRepository;
import com.hifriends.repository.UserRepository;
import com.hifriends.service.api.ChatService;
import com.hifriends.service.api.UserChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author by aleksandrprendota on 25.08.17.
 */
@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserChatService userChatService; ;

    @Override
    public Chat findChatMessageBy2users(long ownerId, long recipientId) {
        Chat chat = chatRepository.findChatByUsers(ownerId, recipientId);
        if (chat == null){
            chat = new Chat();
            chat = chatRepository.save(chat);
            userChatService.createChats(chat, ownerId, recipientId);
        }
        return chat;
    }
}

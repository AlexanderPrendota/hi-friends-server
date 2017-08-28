package com.hifriends.service.impl;

import com.hifriends.model.Chat;
import com.hifriends.model.User;
import com.hifriends.model.UserChat;
import com.hifriends.repository.ChatRepository;
import com.hifriends.repository.UserChatRepository;
import com.hifriends.repository.UserRepository;
import com.hifriends.service.api.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author by aleksandrprendota on 25.08.17.
 */
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserChatRepository userChatRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initGeneralChat() {

    }

    /**
     * Get chat by 2 users
     *
     * @param ownerId     id user1
     * @param recipientId id user2
     * @return chat entity
     */
    @Override
    public Chat findChatMessageBy2users(long ownerId, long recipientId) {
        Chat chat = chatRepository.findChatByUsers(ownerId, recipientId);
        if (chat == null) {
            chat = new Chat();
            chat = chatRepository.save(chat);
            createChats(chat, ownerId, recipientId);
        }
        return chat;
    }

    /**
     * Create user chat between two users
     *
     * @param chat        entity
     * @param ownerId     id user entity
     * @param recipientId id user entity
     */
    @Override
    public void createChats(Chat chat, long ownerId, long recipientId) {
        User recipient = userRepository.findOne(recipientId);
        User owner = userRepository.findOne(ownerId);
        if (recipient != null && owner != null) {
            saveUserChat(recipient, chat);
            saveUserChat(owner, chat);
        }
    }

    private void saveUserChat(User user, Chat chat) {
        UserChat resultChat = new UserChat();
        resultChat.setChat(chat);
        resultChat.setUser(user);
        userChatRepository.save(resultChat);
    }
}

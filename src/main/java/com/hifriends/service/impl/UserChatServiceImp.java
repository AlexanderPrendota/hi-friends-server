package com.hifriends.service.impl;

import com.hifriends.model.Chat;
import com.hifriends.model.User;
import com.hifriends.model.UserChat;
import com.hifriends.repository.ChatRepository;
import com.hifriends.repository.UserChatRepository;
import com.hifriends.repository.UserRepository;
import com.hifriends.service.api.UserChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by aleksandrprendota on 24.08.17.
 */
@Service
public class UserChatServiceImp implements UserChatService {

    @Autowired
    private UserChatRepository userChatRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * get list of userchat entity by chat
     * @param chat entity
     * @return list of userchat entity
     */
    @Override
    public List<UserChat> findByChat(Chat chat) {
        return userChatRepository.findByChat(chat);
    }

    /**
     *  get list of userchat entity by user entity
     * @param user
     * @return list of userchat entity
     */
    @Override
    public List<UserChat> findChatsByUser(User user){
        return userChatRepository.findByUser(user);
    }

    /**
     * Create user chat between two users
     * @param chat
     * @param ownerId
     * @param recipientId
     */
    @Override
    public void createChats(Chat chat, long ownerId, long recipientId) {
        User recipient = userRepository.findOne(recipientId);
        User owner = userRepository.findOne(ownerId);
        UserChat firstChatForOwner = new UserChat();
        UserChat secondChatForUser = new UserChat();
        firstChatForOwner.setChat(chat);
        firstChatForOwner.setUser(owner);
        secondChatForUser.setUser(recipient);
        secondChatForUser.setChat(chat);
        userChatRepository.save(firstChatForOwner);
        userChatRepository.save(secondChatForUser);
    }
}
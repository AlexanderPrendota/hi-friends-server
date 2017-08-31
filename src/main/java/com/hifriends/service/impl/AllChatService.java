package com.hifriends.service.impl;

import com.hifriends.model.Chat;
import com.hifriends.model.User;
import com.hifriends.model.UserChat;
import com.hifriends.repository.ChatRepository;
import com.hifriends.repository.UserChatRepository;
import com.hifriends.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *
 * Hot solution for all chat
 * @author  by aleksandrprendota on 31.08.17.
 */
@Component
public class AllChatService {

    public static long ALL_CHAT_USER_ID = 0;
    public static long ALL_CHAT_ID = 0;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserChatRepository userChatRepository;

    /**
     * Hot solution for testing room conference
     */
    @PostConstruct
    public void createAllChat(){
        User allChatUser = userRepository.findByEmail("allchat");
        if (allChatUser != null) {
            AllChatService.ALL_CHAT_USER_ID = allChatUser.getId();
            AllChatService.ALL_CHAT_ID = chatRepository.findUserChats(allChatUser.getId()).get(0).getId();
        } else {
            Chat chat = new Chat();
            UserChat userChat = new UserChat();
            chat = chatRepository.save(chat);
            User user = User.builder().email("allchat").imagePath("/images/allroom.jpg").name("Jet Family").active(true).build();
            user = userRepository.save(user);
            userChat.setChat(chat);
            userChat.setUser(user);
            userChatRepository.save(userChat);
            AllChatService.ALL_CHAT_USER_ID = user.getId();
            AllChatService.ALL_CHAT_ID = chat.getId();

        }
    }
}

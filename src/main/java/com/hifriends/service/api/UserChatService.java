package com.hifriends.service.api;

import com.hifriends.model.Chat;
import com.hifriends.model.User;
import com.hifriends.model.UserChat;

import java.util.List;

/**
 * @author by aleksandrprendota on 24.08.17.
 */
public interface UserChatService {

    List<UserChat> findChatsByUser(User user);

    Boolean checkUserInChat(Long idUser, Long idChat);

    List<UserChat> findByChat(Chat chat);

    List<UserChat> findChatsUserNot(User user);

    UserChat save(UserChat userChat);

}
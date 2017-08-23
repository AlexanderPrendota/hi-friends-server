package com.hifriends.repository;

import com.hifriends.model.Chat;
import com.hifriends.model.User;
import com.hifriends.model.UserChat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author by aleksandrprendota on 22.08.17.
 */
public interface UserChatRepository extends CrudRepository<UserChat,Long> {
    List<UserChat> findByUser(User user);
    UserChat findByChatAndUser(Chat chat, User user);
    UserChat findByChatAndUserNot(Chat chat, User user);
    List<UserChat> findByChat(Chat chat);
}

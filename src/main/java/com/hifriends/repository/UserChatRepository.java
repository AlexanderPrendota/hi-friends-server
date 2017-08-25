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
}

package com.hifriends.repository;

import com.hifriends.model.Chat;
import com.hifriends.model.Message;
import com.hifriends.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author by aleksandrprendota on 22.08.17.
 */
public interface MessageRepository extends CrudRepository<Message, Long>{
    List<Message> findByChat(Chat chat);
}

package com.hifriends.repository;

import com.hifriends.model.Chat;
import com.hifriends.model.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author by aleksandrprendota on 22.08.17.
 */
@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{
    List<Message> findByChatOrderByTimeStamp(Chat chat);
}

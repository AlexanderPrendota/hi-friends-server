package com.hifriends.repository;

import com.hifriends.model.Chat;
import org.springframework.data.repository.CrudRepository;

/**
 * @author by aleksandrprendota on 22.08.17.
 */
public interface ChatRepository extends CrudRepository<Chat, Long> {
}

package com.hifriends.repository;

import com.hifriends.model.UserChat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by aleksandrprendota on 22.08.17.
 */
@Repository
public interface UserChatRepository extends CrudRepository<UserChat,Long> {
}

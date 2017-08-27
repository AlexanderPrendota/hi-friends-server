package com.hifriends.repository;

import com.hifriends.model.Chat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author by aleksandrprendota on 22.08.17.
 */
@Repository
public interface ChatRepository extends CrudRepository<Chat, Long> {

    /**
     * Get chat between 2 users.
     * @param firstUser id
     * @param secondUser id
     * @return chat entity
     */
    @Query(value = "SELECT c.chat_id FROM users u, user_chats uc, chats c, user_chats uc2, users u2 " +
            "WHERE u.id = uc.user_id and uc.chat_id = c.chat_id AND " +
            "c.chat_id = uc2.chat_id and u2.id = uc2.user_id AND " +
            "u.id = :idUser1 and u2.id = :idUser2"
            , nativeQuery = true)
    Chat findChatByUsers(@Param("idUser1") long firstUser, @Param("idUser2") long secondUser);
}

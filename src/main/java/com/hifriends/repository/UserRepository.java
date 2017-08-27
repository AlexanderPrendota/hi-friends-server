package com.hifriends.repository;

import com.hifriends.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @author by aleksandrprendota on 22.08.17.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByActiveIsTrueAndIdNot(long id);

    /**
     * Chat owner has message notifications.
     * @param userId chat owner id
     * @param date date
     * @return list of users.
     */
    @Query(value =
            "SELECT DISTINCT u.* FROM users u, messages m, user_chats uc " +
            "WHERE " +
            "uc.user_id = :ownerId AND " +
            "m.time_stamp < :messageTime AND " +
            "m.sender_id <> :ownerId AND " +
            "u.id = m.sender_id"
            , nativeQuery = true)
    List<User> findLastUsersMessage(@Param("ownerId") long userId,
                                    @Param("messageTime") Date date);
}

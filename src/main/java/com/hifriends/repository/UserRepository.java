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
     * Getting all users who sent the message to ownerId.
     * where message date more than messageTime
     *
     * @param userId chat owner id
     * @param date   date
     * @return list of users.
     */
    @Query(value =
            "SELECT DISTINCT u2.* FROM users u, users u2, messages m, user_chats uc " +
                    "where uc.user_id = u.id AND " +
                    "m.chat_id = uc.chat_id and " +
                    "uc.user_id = :ownerId and " +
                    "m.time_stamp > :messageTime and " +
                    "m.sender_id <> u.id and " +
                    "m.sender_id = u2.id and " +
                    "u2.id <> :ownerId"
            , nativeQuery = true)
    List<User> findLastUsersMessage(@Param("ownerId") long userId,
                                    @Param("messageTime") Date date);
}

// it works too

//    select distinct u.*
//        from users u
//        ,messages m
//        ,(
//        select distinct uc.chat_id
//        from user_chats uc
//        where uc.user_id = :ownerId
//        ) c
//        ,user_chats uc
//        where m.sender_id <> :ownerId
//        and m.chat_id = uc.chat_id
//        and uc.chat_id = c.chat_id
//        and u.id = uc.user_id
//        and u.id <> :ownerId
//        and time_stamp > :messageTime
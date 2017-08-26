package com.hifriends.repository;

import com.hifriends.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @author by aleksandrprendota on 22.08.17.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByActiveIsTrueAndIdNot(long id);
}

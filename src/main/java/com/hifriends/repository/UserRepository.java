package com.hifriends.repository;

import com.hifriends.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author by aleksandrprendota on 22.08.17.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByActiveIsTrue();
}

package com.hifriends.repository;

import com.hifriends.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author by aleksandrprendota on 22.08.17.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByActiveIsTrueAndIdNot(long id);

}
package com.hifriends.service.api;

import com.hifriends.model.User;
import java.util.List;

/**
 * @author  by aleksandrprendota on 22.08.17.
 */
public interface UserService {


    User getUserById(long id);

    List<User> getAllUsers();

    User getUserByEmail(String email);

    User addUser(User user);

    List<User> getAllActiveUsers();

    void updateUserStatus(User user);



}

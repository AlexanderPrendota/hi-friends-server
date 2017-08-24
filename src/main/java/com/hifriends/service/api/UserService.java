package com.hifriends.service.api;

import com.hifriends.model.User;
import com.hifriends.model.dto.UserDTO;

import java.util.List;

/**
 * @author  by aleksandrprendota on 22.08.17.
 */
public interface UserService {


    List<User> getAllUsers();

    User getUserByEmail(String email);

    List<User> getAllActiveUsers();

    void updateUserStatus(String userEmail);

    UserDTO registrateUser(String name, String email, String avatar);
}

package com.hifriends.service.impl;

import com.hifriends.model.User;
import com.hifriends.repository.UserRepository;
import com.hifriends.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation UserService
 * @author  by aleksandrprendota on 22.08.17.
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    /**
     * Get user by id from db
     * @param id
     * @return user entity
     */
    @Override
    public User getUserById(long id) {
        return userRepository.findOne(id);
    }

    /**
     * Get list of all user from db
     * @return list of users
     */
    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    /**
     * Get user by email
     * @param email
     * @return user entity
     */
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Add new user to chat
     * @param user
     * @return saved user
     */
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Get users with active status = true
     * @return list of user with true activity
     */
    @Override
    public List<User> getAllActiveUsers() {
        return userRepository.findByActiveIsTrue();
    }

    /**
     * Update user status to false
     */
    @Override
    public void updateUserStatus(User user) {
        user.setActive(false);
        userRepository.save(user);
    }

}

package com.hifriends.service.impl;

import com.hifriends.model.User;
import com.hifriends.model.dto.UserDTO;
import com.hifriends.repository.UserRepository;
import com.hifriends.service.api.UserService;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;


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
    public void updateUserStatus(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if(user != null){
            user.setActive(false);
            userRepository.save(user);
        }
    }

    /**
     * Registratite user in systems and set online status
     * @param name
     * @param email
     * @param avatar
     * @return
     */
    @Override
    public UserDTO registrateUser(String name, String email, String avatar) {
        User user = userRepository.findByEmail(email);
        if (user == null){
            user = new User();
            user.setEmail(email);
            user.setName(name);
            user.setImagePath(avatar);
            user.setActive(true);
        } else {
            user.setActive(true);
        }
        userRepository.save(user);
        return convertToDto(user);
    }


    private UserDTO convertToDto(User user){
        return modelMapper.map(user, UserDTO.class);
    }

}

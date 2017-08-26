package com.hifriends.service.impl;

import com.hifriends.model.User;
import com.hifriends.model.dto.UserDto;
import com.hifriends.repository.UserRepository;
import com.hifriends.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation UserService
 *
 * @author by aleksandrprendota on 22.08.17.
 */

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get users with active status = true
     *
     * @return list of user with true activity
     */
    @Override
    public List<User> getAllActiveUsers(long id) {
        //TODO Throw exceptions
        return userRepository.findByActiveIsTrueAndIdNot(id);
    }

    /**
     * Update user status to false
     */
    @Override
    public void updateUserStatus(long id) {
        User user = userRepository.findOne(id);
        if (user != null) {
            user.setActive(false);
            userRepository.save(user);
        }
    }

    /**
     * Registratite user in systems and set online status
     *
     * @param name
     * @param email
     * @param avatar
     * @return
     */
    @Override
    public UserDto registrateUser(String name, String email, String avatar) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = User.builder().email(email).imagePath(avatar).name(name).active(true).build();
        } else {
            user.setActive(true);
        }
        userRepository.save(user);
        return convertToDto(user);
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}

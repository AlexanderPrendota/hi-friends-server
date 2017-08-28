package com.hifriends.service.impl;

import com.hifriends.model.User;
import com.hifriends.model.dto.UserDto;
import com.hifriends.repository.UserRepository;
import com.hifriends.service.api.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation UserService
 *
 * @author by aleksandrprendota on 22.08.17.
 */

@Service
public class UserServiceImpl implements UserService {

    private static final long TEN_SECONDS = 10000L;

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
    public List<UserDto> getAllActiveUsers(long id) {
        return userRepository.findByActiveIsTrueAndIdNot(id)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
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
     * @param name   name of user
     * @param email  email
     * @param avatar image path
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

    /**
     * Chat owner has message notifications.
     * unlike notification with current companion.
     * Get notification only for 30 sec.
     *
     * @param idOwner chat owner id
     * @return list of users.
     */
    @Override
    public List<UserDto> getUsersIdsByNewMessages(long idOwner) {
        Date currentTime = new Date();
        long time = currentTime.getTime() - UserServiceImpl.TEN_SECONDS;
        return userRepository.findLastUsersMessage(idOwner, new Date(time))
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}

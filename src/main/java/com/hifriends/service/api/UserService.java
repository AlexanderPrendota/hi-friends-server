package com.hifriends.service.api;

import com.hifriends.model.dto.UserDto;
import java.util.List;

/**
 * @author  by aleksandrprendota on 22.08.17.
 */
public interface UserService {

    List<UserDto> getAllActiveUsers(long id);

    void updateUserStatusToFalse(long id);

    UserDto registrateUser(String name, String email, String avatar);

}

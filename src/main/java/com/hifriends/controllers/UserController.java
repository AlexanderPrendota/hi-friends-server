package com.hifriends.controllers;

import com.hifriends.model.dto.UserDto;
import com.hifriends.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for user operation
 *
 * @author by aleksandrprendota on 24.08.17.
 */
@RestController
@RequestMapping(value = "api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get list of active user in chat
     * id = -1 => user not in system
     * @return list users or empty list if user doesn't register
     */
    @RequestMapping(value = "/active/{id}", method = RequestMethod.GET)
    public List<UserDto> getActiveUsers(@PathVariable long id) {
        List<UserDto> activeUsers = new ArrayList<>();
        if (id == -1){
            return activeUsers;
        } else {
            activeUsers = userService.getAllActiveUsers(id);
            return activeUsers;
        }
    }
}

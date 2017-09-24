package com.hifriends.controllers;

import com.hifriends.model.dto.UserDto;
import com.hifriends.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Login controller for user registration by google account
 *
 * @author by aleksandrprendota on 22.08.17.
 */
@RestController
public class LoginController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Controller for registration your in chat
     *
     * @return userDto
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public UserDto login(@RequestBody UserDto userDto) {
        UserDto chatOwner = userService.registrateUser(userDto.getName(), userDto.getEmail(), userDto.getImagePath());
        return chatOwner;
    }

    /**
     * Logout user after he left the chat.
     * Set his active to false
     *
     * @param id user for logout
     */
    @RequestMapping(value = "/logout/{id}", method = RequestMethod.GET)
    public void updateUserStatus(@PathVariable long id) {
        userService.updateUserStatus(id);
    }
}
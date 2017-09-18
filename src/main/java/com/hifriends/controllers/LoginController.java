package com.hifriends.controllers;

import com.hifriends.model.dto.UserDto;
import com.hifriends.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Login controller for user registration by google account
 *
 * @author by aleksandrprendota on 22.08.17.
 */
@Controller
@RequestMapping
public class LoginController {

    private UserService userService;


    /**
     * Controller for registration user in chat
     *
     * @return User Dto
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public UserDto login(@RequestBody UserDto userDto) {
        return userService.registrateUser(userDto.getName(), userDto.getEmail(), userDto.getImagePath());
    }

    /**
     * Logout user after he left the chat.
     * Set his active to false
     *
     * @param id
     */

    @RequestMapping(value = "/logout/{id}", method = RequestMethod.POST)
    public void updateUserStatus(@PathVariable long id) {
        userService.updateUserStatus(id);
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
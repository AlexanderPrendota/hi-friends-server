package com.hifriends.controllers;

import com.hifriends.model.User;
import com.hifriends.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * REST controller for user operation
 * @author by aleksandrprendota on 24.08.17.
 */
@RestController
@RequestMapping(value = "api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Get list of active user in chat
     * @return
     */
    @RequestMapping(value = "/active/{id}", method = RequestMethod.GET)
    public List<User> getActiveUsers(@PathVariable long id){
        return userService.getAllActiveUsers(id);
    }

    /**
     * Logout user after he left the chat.
     * Set his active to false
     * @param id
     */
    // TODO: send id
    @RequestMapping(value = "/logout/{id}", method = RequestMethod.POST)
    public void updateUserStatus(@PathVariable long id){
        userService.updateUserStatus(id);
    }
}

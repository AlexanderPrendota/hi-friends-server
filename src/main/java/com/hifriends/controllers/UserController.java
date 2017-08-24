package com.hifriends.controllers;

import com.hifriends.model.User;
import com.hifriends.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public List<User> getActiveUsers(){
        return userService.getAllActiveUsers();
    }

    /**
     * Logout user after he left the chat.
     * Set his active to false
     * @param email
     * @return 200 status
     */
    @RequestMapping(value = "/logout/{email:.*}", method = RequestMethod.POST)
    public ResponseEntity<?> updateUserStatus(@PathVariable String email){
        userService.updateUserStatus(email);
        return ResponseEntity.ok("User was updated");
    }
}
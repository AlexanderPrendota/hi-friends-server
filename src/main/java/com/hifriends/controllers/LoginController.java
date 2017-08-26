package com.hifriends.controllers;

import com.hifriends.model.dto.UserDto;
import com.hifriends.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Login controller for user registration by google account
 *
 * @author by aleksandrprendota on 22.08.17.
 */
@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = "chat", method = RequestMethod.POST)
    public String login(@RequestParam(value = "name") String name,
                        @RequestParam(value = "email") String email,
                        @RequestParam(value = "avatar") String avatar,
                        Model model) {
        UserDto chatOwner = userService.registrateUser(name, email, avatar);
        model.addAttribute("chatOwner", chatOwner);
        return "chat";
    }
}
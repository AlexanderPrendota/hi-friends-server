package com.hifriends.controllers;

import com.hifriends.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author  by aleksandrprendota on 22.08.17.
 *
 * Login controller for user registration by google account
 */
@Controller
@RequestMapping
public class LoginController {

    @RequestMapping(path = "chat", method = RequestMethod.POST)
    public String login(@RequestParam(value = "name") final String name,
                        @RequestParam(value = "email") final String email,
                        @RequestParam(value = "avatar") final String avatar,
                        Model model) {
        System.out.println(name);
        System.out.println(email);
        System.out.println(avatar);
        User chatOwner = new User(1,email,name,avatar,false);
        model.addAttribute("chatOwner", chatOwner);
        return "chat";
    }
}
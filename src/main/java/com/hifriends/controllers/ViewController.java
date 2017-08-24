package com.hifriends.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author by aleksandrprendota on 22.08.17.
 */
@Controller
@RequestMapping
public class ViewController {

    @RequestMapping("/")
    public String homeView() {
        return "home";
    }

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public String load(@RequestParam(value = "ownerId") final Long ownerId,
                       @RequestParam(value = "userId") final Long userId, final Model model) {
        return "messages :: messagesList";
    }

}
package com.hifriends.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/message")
    public String messageView() {
        return "/message";
    }

}
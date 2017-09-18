package com.hifriends.controllers;


import com.hifriends.model.dto.UserDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author Alexander Prendota on 18.09.2017.
 */

@Controller
public class WebSocketsController {

    @MessageMapping(value = "/notify")
    @SendTo("topic/reload")
    public UserDto notificationUsers(UserDto userDto){
        return userDto;
    }
}

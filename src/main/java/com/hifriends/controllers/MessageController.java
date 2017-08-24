package com.hifriends.controllers;

import com.hifriends.model.dto.MessageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for message operation
 * @author  by aleksandrprendota on 24.08.17.
 */
@RestController
public class MessageController {

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody MessageDTO messageDTO){
        System.out.println(messageDTO);
        return ResponseEntity.ok("message was sent");
    }
}

package com.hifriends.controllers;

import com.hifriends.model.dto.MessagePostDto;
import com.hifriends.service.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for message operation
 *
 * @author by aleksandrprendota on 24.08.17.
 */
@RestController
@RequestMapping(value = "api/message")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> save(@RequestBody MessagePostDto messageDTO) {
        messageService.postMessage(messageDTO);
        return ResponseEntity.ok("Message was sent");
    }
}
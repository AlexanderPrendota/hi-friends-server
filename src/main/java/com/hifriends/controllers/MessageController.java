package com.hifriends.controllers;

import com.hifriends.model.Chat;
import com.hifriends.model.dto.MessageDto;
import com.hifriends.model.dto.MessagePostDto;
import com.hifriends.service.api.ChatService;
import com.hifriends.service.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for message operation
 *
 * @author by aleksandrprendota on 24.08.17.
 */
@RestController
@RequestMapping(value = "api/message")
public class MessageController {

    private MessageService messageService;

    private ChatService chatService;

    /**
     * Controller for saving message to db
     *
     * @param messageDTO
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public MessageDto save(@RequestBody MessagePostDto messageDTO) {
        return messageService.postMessage(messageDTO);
    }

    @RequestMapping(value = "/chat/owner/{ownerId}/user/{userId}", method = RequestMethod.POST)
    public long getChatBetweenTwoUsers(@PathVariable long ownerId, @PathVariable long userId){
        Chat chat = chatService.findChatMessageBy2users(ownerId, userId);
        return chat.getId();
    }

    @RequestMapping(value = "messages/{ownerId}/{userId}", method = RequestMethod.GET)
    public List<MessageDto> loadMessage(@PathVariable long ownerId, @PathVariable long userId){
        Chat chat = chatService.findChatMessageBy2users(ownerId, userId);
        return messageService.findByChat(chat);
    }

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }
}
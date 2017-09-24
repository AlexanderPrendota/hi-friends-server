package com.hifriends.controllers;

import com.hifriends.model.Chat;
import com.hifriends.model.dto.MessageDto;
import com.hifriends.model.dto.MessagePostDto;
import com.hifriends.service.api.ChatService;
import com.hifriends.service.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
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

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * controller for saving message to db
     *
     * @param messagePostDto
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public MessageDto save(@RequestBody MessagePostDto messagePostDto) {
        MessageDto message = messageService.postMessage(messagePostDto);
        simpMessagingTemplate
                .convertAndSendToUser(String.valueOf(messagePostDto.getRecipientId()), "/queue/reply", message);
        return message;
    }

    /**
     * Controller for getting message list between two user
     *
     * @param ownerId chat owner id
     * @param userId  his chat partner
     * @return
     */
    @RequestMapping(value = "/messages/{ownerId}/{userId}", method = RequestMethod.GET)
    public List<MessageDto> loadMessages(@PathVariable long ownerId, @PathVariable long userId) {
        Chat chat = chatService.findChatMessageBy2users(ownerId, userId);
        return messageService.findByChat(chat);
    }

    /**
     * Get chat Between two user
     *
     * @return chat id
     */
    @RequestMapping(value = "/chat/owner/{ownerId}/user/{userId}", method = RequestMethod.GET)
    public long getChatBetweenTwoUsers(@PathVariable long ownerId, @PathVariable long userId) {
        Chat chat = chatService.findChatMessageBy2users(ownerId, userId);
        return chat.getId();
    }
}
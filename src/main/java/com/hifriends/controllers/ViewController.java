package com.hifriends.controllers;

import com.hifriends.model.Chat;
import com.hifriends.model.dto.MessageDto;
import com.hifriends.service.api.ChatService;
import com.hifriends.service.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author by aleksandrprendota on 22.08.17.
 */
@Controller
@RequestMapping
public class ViewController {

    private MessageService messageService;

    private ChatService chatService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    /**
     * Controller for getting message list between two user
     * @param ownerId chat owner id
     * @param userId his chat partner
     * @param model
     * @return
     */
    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public String loadMessages(@RequestParam(value = "ownerId") long ownerId,
                               @RequestParam(value = "userId") long userId, Model model) {
        Chat chat = chatService.findChatMessageBy2users(ownerId, userId);
        List<MessageDto> messages = messageService.findByChat(chat);
        model.addAttribute("chatId", chat.getId());
        model.addAttribute("messages", messages);
        model.addAttribute("from", ownerId);
        return "messages :: messagesList";
    }
}
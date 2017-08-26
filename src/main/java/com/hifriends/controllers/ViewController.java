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

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatService chatService;

    @RequestMapping(value = "/messages", method = RequestMethod.POST)
    public String load(@RequestParam(value = "ownerId") long ownerId,
                       @RequestParam(value = "userId") long userId, Model model) {
        Chat chat = chatService.findChatMessageBy2users(ownerId, userId);
        List<MessageDto> messages = messageService.findByChat(chat);
        model.addAttribute("chatId", chat.getId());
        model.addAttribute("messages", messages);
        model.addAttribute("from", ownerId);
        return "messages :: messagesList";
    }

}
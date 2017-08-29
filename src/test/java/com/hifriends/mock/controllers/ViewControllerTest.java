package com.hifriends.mock.controllers;

import com.hifriends.controllers.ViewController;
import com.hifriends.model.Chat;
import com.hifriends.service.api.ChatService;
import com.hifriends.service.api.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

/**
 * @author aleksandrprendota on 28.08.17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ViewControllerTest {

    @Mock
    private MessageService messageService;

    @Mock
    private ChatService chatService;

    @InjectMocks
    private ViewController viewController;

    @Test
    public void loadMessagesTest() {
        when(chatService.findChatMessageBy2users(1L, 2L)).thenReturn(new Chat());
        when(messageService.findByChat(new Chat())).thenReturn(new ArrayList<>());
        viewController.loadMessages(1L, 2L, mock(Model.class));
        verify(chatService).findChatMessageBy2users(1L, 2L);
        verify(messageService).findByChat(new Chat());
    }
}

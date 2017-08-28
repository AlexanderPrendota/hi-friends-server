package com.hifriends.mock.controllers;

import com.hifriends.controllers.MessageController;
import com.hifriends.model.dto.MessagePostDto;
import com.hifriends.service.api.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

/**
 * @author aleksandrprendota on 28.08.17.
 */

@RunWith(MockitoJUnitRunner.class)
public class MessageControllerTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    @Test
    public void saveMessage(){
       doNothing().when(messageService).postMessage(new MessagePostDto());
       messageController.save(new MessagePostDto());
       verify(messageService, times(1)).postMessage(new MessagePostDto());
    }
}

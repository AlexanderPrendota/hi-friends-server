package com.hifriends.mock.service;

import com.hifriends.exception.PostMessageException;
import com.hifriends.model.Chat;
import com.hifriends.model.User;
import com.hifriends.model.dto.MessageDto;
import com.hifriends.model.dto.MessagePostDto;
import com.hifriends.repository.ChatRepository;
import com.hifriends.repository.MessageRepository;
import com.hifriends.repository.UserRepository;
import com.hifriends.service.impl.MessageServiceImp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.*;

/**
 * @author by aleksandrprendota on 26.08.17.
 */

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceMockTest {

    private Chat chat;
    private User user;
    private MessagePostDto messagePostDto;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MessageServiceImp messageService;

    @Before
    public void setup() {
        chat = new Chat();
        chat.setId(1L);
        user = new User();
        user.setId(2L);
        user.setEmail("email");
        user.setName("name");
        user.setActive(true);
        messagePostDto = new MessagePostDto();
        messagePostDto.setChatId(1L);
        messagePostDto.setSenderId(2L);
        messagePostDto.setTimeStamp(new Date());
        messagePostDto.setText("text");
    }

    @Test(expected = PostMessageException.class)
    public void postMessageWithException() throws Exception {
        when(userRepository.findByEmail("email")).thenReturn(user);
        when(chatRepository.findOne(1L)).thenReturn(chat);
        messageService.postMessage(messagePostDto);
        verify(userRepository).findOne(user.getId());
        verify(chatRepository.findOne(chat.getId()));

    }

    @Test
    public void postMessage() throws Exception {
        when(userRepository.findOne(2L)).thenReturn(user);
        when(chatRepository.findOne(1L)).thenReturn(chat);
        messageService.postMessage(messagePostDto);
        verify(userRepository).findOne(user.getId());
        verify(chatRepository).findOne(chat.getId());
    }

    @Test
    public void findByChat() throws Exception {
        when(messageRepository.findByChatOrderByTimeStamp(chat)).thenReturn(new ArrayList<>());
        messageService.findByChat(chat);
        verify(messageRepository).findByChatOrderByTimeStamp(chat);
    }

}

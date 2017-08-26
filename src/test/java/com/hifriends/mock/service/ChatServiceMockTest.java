package com.hifriends.mock.service;

import com.hifriends.model.Chat;
import com.hifriends.model.User;
import com.hifriends.model.UserChat;
import com.hifriends.repository.ChatRepository;
import com.hifriends.repository.UserChatRepository;
import com.hifriends.repository.UserRepository;
import com.hifriends.service.impl.ChatServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * @author by aleksandrprendota on 26.08.17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ChatServiceMockTest {

    private Chat chat;
    private User userOne;
    private User userTwo;
    private UserChat userChat;

    @Mock
    private ChatRepository chatRepository;

    @Mock
    private UserChatRepository userChatRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ChatServiceImpl chatService;

    @Before
    public void setup() {
        chat = new Chat();
        chat.setId(1L);
        userOne = new User();
        userTwo = new User();
        userTwo.setId(2L);
        userOne.setId(1L);
        userChat = new UserChat();
    }

    @Test
    public void findChatMessageBy2users() throws Exception {
        when(chatRepository.findChatByUsers(2L, 3L)).thenReturn(chat);
        chatService.findChatMessageBy2users(2L, 3L);
        verify(chatRepository).findChatByUsers(2L, 3L);
    }

    @Test
    public void findChatMessageBy2usersNot() throws Exception {
        when(chatRepository.findChatByUsers(2L, 3L)).thenReturn(null);
        chatService.findChatMessageBy2users(2L, 3L);
        verify(chatRepository).findChatByUsers(2L, 3L);
    }


    @Test
    public void createChats() throws Exception {
        when(userRepository.findOne(1L)).thenReturn(userOne);
        when(userRepository.findOne(2L)).thenReturn(userTwo);

        chatService.createChats(chat, 1L, 2L);
        verify(userRepository).findOne(userOne.getId());
        verify(userRepository).findOne(userTwo.getId());

    }

}

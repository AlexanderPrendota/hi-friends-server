package com.hifriends.unit.service;

import com.hifriends.model.Chat;
import com.hifriends.repository.ChatRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author by aleksandrprendota on 26.08.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ChatServiceTest {
    @Autowired
    private ChatRepository chatRepository;

    @Test
    public void findChatMessageBy2users() throws Exception {
        Chat chat = chatRepository.findChatByUsers(1L, 2L);
        Assert.assertNotNull(chat);
    }

}
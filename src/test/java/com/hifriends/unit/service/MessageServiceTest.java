package com.hifriends.unit.service;

import com.hifriends.model.Chat;
import com.hifriends.model.Message;
import com.hifriends.repository.ChatRepository;
import com.hifriends.repository.MessageRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author by aleksandrprendota on 26.08.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Test
    @Ignore
    public void findByChat() throws Exception {
        Chat chat = chatRepository.findOne(35L);
        Assert.assertNotNull(chat);
        List<Message> message = messageRepository.findByChatOrderByTimeStamp(chat);
        Assert.assertTrue(message.size() > 0);
    }

}
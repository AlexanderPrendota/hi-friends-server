package com.hifriends.unit.dao;

import com.hifriends.repository.ChatRepository;
import com.hifriends.repository.MessageRepository;
import com.hifriends.repository.UserChatRepository;
import com.hifriends.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author by aleksandrprendota on 26.08.17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserChatRepository userChatRepository;

    @Test
    public void userRepositoryTest() {
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void chatRepositoryTest() {
        Assert.assertNotNull(chatRepository);
    }

    @Test
    public void messageRepositoryTest() {
        Assert.assertNotNull(messageRepository);
    }

    @Test
    public void userChatRepositoryTest() {
        Assert.assertNotNull(userChatRepository);
    }

}

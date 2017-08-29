package com.hifriends.unit.models;

import com.hifriends.model.Chat;
import com.hifriends.model.Message;
import com.hifriends.model.User;
import com.hifriends.model.UserChat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * @author by aleksandrprendota on 26.08.17.
 */
public class ModelsTest {

    private Message message;
    private UserChat userChat;
    private User user;
    private Chat chat;

    @Before
    public void setup() {
        message = new Message();
        userChat = new UserChat();
        chat = new Chat();
        chat.setId(1L);
        user = new User();
        user.setName("name");
    }

    @Test
    public void testMessageModel() {

        message.setText("text");
        message.setTimeStamp(new Date());
        message.setId(1L);
        message.setChat(chat);
        message.setSender(user);

        Assert.assertEquals(message.getText(), "text");
        Assert.assertEquals(message.getId(), 1L);
        Assert.assertEquals(message.getChat().getId(), 1L);
        Assert.assertEquals(message.getSender().getName(), "name");

    }

    @Test
    public void testUserChatModel() {
        userChat.setUser(user);
        userChat.setChat(chat);
        userChat.setId(1L);

        Assert.assertEquals(userChat.getId(), 1L);
        Assert.assertEquals(userChat.getUser().getName(), "name");
        Assert.assertEquals(userChat.getChat().getId(), 1L);
    }
}

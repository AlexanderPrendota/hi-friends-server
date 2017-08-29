package com.hifriends.unit.models;

import com.hifriends.model.dto.MessageDto;
import com.hifriends.model.dto.MessagePostDto;
import com.hifriends.model.dto.UserDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * @author by aleksandrprendota on 26.08.17.
 */
public class DtoTest {
    private MessagePostDto messagePostDto;
    private UserDto userDto;
    private MessageDto messageDto;

    @Before
    public void setup() {
        messageDto = new MessageDto();
        userDto = new UserDto();
        messagePostDto = new MessagePostDto();
    }

    @Test
    public void messagePostDtoTest() {
        messagePostDto.setText("text");
        messagePostDto.setTimeStamp(new Date());
        messagePostDto.setChatId(1L);
        messagePostDto.setSenderId(1L);

        Assert.assertEquals(messagePostDto.getSenderId(), 1L);
        Assert.assertEquals(messagePostDto.getChatId(), 1L);
        Assert.assertEquals(messagePostDto.getText(), "text");
    }

    @Test
    public void messageDtoTest() {
        messageDto.setSenderId(1L);
        messageDto.setSenderImagePath("path");
        messageDto.setSenderName("name");
        messageDto.setTimeStamp(new Date());
        messageDto.setText("text");

        Assert.assertEquals(messageDto.getSenderId(), 1L);
        Assert.assertEquals(messageDto.getText(), "text");
        Assert.assertEquals(messageDto.getSenderImagePath(), "path");
        Assert.assertEquals(messageDto.getSenderName(), "name");

    }

    @Test
    public void testUserDro() {
        userDto.setActive(true);
        userDto.setEmail("email");
        userDto.setId(1L);
        userDto.setImagePath("path");
        userDto.setName("name");

        Assert.assertEquals(userDto.getName(), "name");
        Assert.assertEquals(userDto.getImagePath(), "path");
        Assert.assertEquals(userDto.getEmail(), "email");
        Assert.assertEquals(userDto.getId(), 1L);
        Assert.assertEquals(userDto.isActive(), true);

    }
}

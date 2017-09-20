package com.hifriends.service.api;

import com.hifriends.model.Chat;
import com.hifriends.model.dto.MessageDto;
import com.hifriends.model.dto.MessagePostDto;

import java.util.List;

/**
 * @author by aleksandrprendota on 24.08.17.
 */
public interface MessageService {

    MessageDto postMessage(MessagePostDto messagePostDto);

    List<MessageDto> findByChat(Chat id);

    MessageDto getMessageById(long id);
}
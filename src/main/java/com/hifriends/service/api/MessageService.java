package com.hifriends.service.api;

import com.hifriends.model.Chat;
import com.hifriends.model.Message;
import com.hifriends.model.dto.MessageDTO;

import java.util.List;

/**
 * @author by aleksandrprendota on 24.08.17.
 */
public interface MessageService {

    Message postMessage(MessageDTO messageDTO);

    List<MessageDTO> findByChat(Chat id);

}
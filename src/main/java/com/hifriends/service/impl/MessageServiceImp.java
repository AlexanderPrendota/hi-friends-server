package com.hifriends.service.impl;

import com.hifriends.model.Chat;
import com.hifriends.model.Message;
import com.hifriends.model.User;
import com.hifriends.model.dto.MessageDto;
import com.hifriends.model.dto.MessagePostDto;
import com.hifriends.repository.ChatRepository;
import com.hifriends.repository.MessageRepository;
import com.hifriends.repository.UserRepository;
import com.hifriends.service.api.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by aleksandrprendota on 24.08.17.
 */
@Service
public class MessageServiceImp implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Post message entity to db
     * @param messagePostDto
     * @return new Message entity
     */
    @Override
    public void postMessage(MessagePostDto messagePostDto) {
        // TODO: throw exception
        User sender = userRepository.findOne(messagePostDto.getSenderId());
        Chat chat = chatRepository.findOne(messagePostDto.getChatId());
        if (sender != null && chat != null){
            Message message = Message.builder()
                    .chat(chat)
                    .sender(sender)
                    .text(messagePostDto.getText())
                    .timeStamp(messagePostDto.getTimeStamp()).build();
            messageRepository.save(message);
        }
    }

    /**
     * Getting list of message by chat entity
     * @param id chat entity
     * @return
     */
    @Override
    public List<MessageDto> findByChat(Chat id) {
        return messageRepository.findByChat(id)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private MessageDto convertToDto(Message message){
        return modelMapper.map(message, MessageDto.class);
    }
}
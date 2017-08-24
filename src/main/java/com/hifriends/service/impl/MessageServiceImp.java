package com.hifriends.service.impl;

import com.hifriends.model.Chat;
import com.hifriends.model.Message;
import com.hifriends.model.dto.MessageDTO;
import com.hifriends.model.User;
import com.hifriends.model.UserChat;
import com.hifriends.repository.ChatRepository;
import com.hifriends.repository.MessageRepository;
import com.hifriends.repository.UserChatRepository;
import com.hifriends.repository.UserRepository;
import com.hifriends.service.api.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    private UserChatRepository userChatRepository;

    /**
     * Post message entity to db
     * of creating chat with the first message
     * @param messageDTO
     * @return new Message entity
     */
    @Override
    public Message postMessage(MessageDTO messageDTO) {
        User sender = userRepository.findOne(messageDTO.getSenderId());
        User recipient = userRepository.findOne(messageDTO.getRecipientId());
        Message message = new Message();
        message.setText(messageDTO.getText());
        message.setTimeStamp(messageDTO.getTimeStamp());
        message.setSender(sender);
        UserChat filteredChat = null;
        if(messageDTO.getChatId() != null){
            Chat chat = chatRepository.findOne(messageDTO.getChatId());
            if(chat != null){
                message.setChat(chat);
                return messageRepository.save(message);
            }
        }
        List<UserChat> userChats = userChatRepository.findByUser(sender);
        for (UserChat userChat : userChats) {
            filteredChat = userChatRepository.findByChatAndUser(userChat.getChat(), recipient);
            if(filteredChat != null) {
                break;
            }
        }
        if(filteredChat != null){
            message.setChat(filteredChat.getChat());
        } else {
            Chat chat = chatRepository.save(new Chat());
            UserChat first = new UserChat();
            first.setChat(chat);
            first.setUser(sender);
            userChatRepository.save(first);
            UserChat second = new UserChat();
            second.setUser(recipient);
            second.setChat(chat);
            userChatRepository.save(second);
            message.setChat(chat);
        }
        return messageRepository.save(message);
    }

    /**
     * Getting list of message by chat entity
     * @param id chat entity
     * @return
     */
    @Override
    public List<Message> findByChat(Chat id) {
        return messageRepository.findByChat(id);
    }


    @Override
    public List<Message> findBySender(User user) {
        return messageRepository.findBySender(user);
    }

}
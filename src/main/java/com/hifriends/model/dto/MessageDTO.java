package com.hifriends.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author by aleksandrprendota on 24.08.17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private long recipientId;
    private long senderId;
    private String text;
    private Date timeStamp;
    private Long chatId;
}
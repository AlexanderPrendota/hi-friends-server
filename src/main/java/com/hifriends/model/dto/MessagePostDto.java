package com.hifriends.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author by aleksandrprendota on 25.08.17.
 */
@Data
@NoArgsConstructor
public class MessagePostDto {
    private long senderId;
    private long chatId;
    private String text;
    private Date timeStamp;
}

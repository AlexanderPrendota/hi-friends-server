package com.hifriends.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alexander Prendota on 20.09.17.
 */
@Data
@NoArgsConstructor
public class NotificationDto {
    long idMessage;
    long recipient;
}

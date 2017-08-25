package com.hifriends.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author by aleksandrprendota on 24.08.17.
 */
@NoArgsConstructor
@Data
public class UserDto {
    long id;
    String name;
    String email;
    String imagePath;
    boolean active;
}

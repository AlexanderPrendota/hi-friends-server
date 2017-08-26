package com.hifriends.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author by aleksandrprendota on 22.08.17.
 */
@Data
@Entity(name = "CHATS")
@NoArgsConstructor
public class Chat {

    @Id
    @GeneratedValue
    @Column(name = "CHAT_ID", nullable = false)
    private long id;
}

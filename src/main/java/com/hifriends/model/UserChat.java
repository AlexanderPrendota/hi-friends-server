package com.hifriends.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author by aleksandrprendota on 22.08.17.
 */
@Data
@NoArgsConstructor
@Entity(name = "USER_CHATS")
public class UserChat {

    @Id
    @GeneratedValue
    @Column(name = "USER_CHAT_ID", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "CHAT_ID")
    private Chat chat;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

}
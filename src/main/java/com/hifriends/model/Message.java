package com.hifriends.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author by aleksandrprendota on 22.08.17.
 */
@Data
@Builder
@AllArgsConstructor
@Entity(name = "MESSAGES")
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    @Column(name = "MESSAGE_ID", nullable = false)
    private long id;

    @Column(name = "TEXT")
    @NotNull
    private String text;

    @Column(name = "TIME_STAMP")
    @NotNull
    private Date timeStamp;

    @ManyToOne
    @JoinColumn(name = "SENDER_ID")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "CHAT_ID")
    private Chat chat;
}
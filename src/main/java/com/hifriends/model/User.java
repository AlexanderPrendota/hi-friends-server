package com.hifriends.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author by aleksandrprendota on 22.08.17.
 */

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    public long id;

    @Column(name = "EMAIL", unique = true)
    public String email;

    @Column(name = "NAME")
    public String name;

    @Column(name = "IMAGE_PATH")
    public String imagePath;

    @Column(name = "ACTIVE")
    public boolean active = false;


}
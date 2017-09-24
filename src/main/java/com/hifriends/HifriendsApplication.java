package com.hifriends;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.hifriends")
@SpringBootApplication
public class HifriendsApplication {
    public static void main(String[] args) {
        SpringApplication.run(HifriendsApplication.class, args);
    }
}

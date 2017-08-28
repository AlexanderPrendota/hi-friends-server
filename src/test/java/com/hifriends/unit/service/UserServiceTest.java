package com.hifriends.unit.service;

import com.hifriends.model.User;
import com.hifriends.repository.UserRepository;
import com.hifriends.service.api.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author by aleksandrprendota on 26.08.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void setup() {
        user = User.builder().email("S@s").imagePath("/path").name("Sasha").active(true).build();
        userRepository.save(user);

    }

    @Test
    public void updateUserStatus() throws Exception {
        long userId = userRepository.findByEmail("S@s").getId();
        userService.updateUserStatus(userId);
        User updatedUser = userRepository.findOne(userId);
        Assert.assertFalse(updatedUser.isActive());
    }


    @After
    public void after() {
        userRepository.delete(user);
    }

}
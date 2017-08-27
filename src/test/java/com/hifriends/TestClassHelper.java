package com.hifriends;

import com.hifriends.model.User;
import com.hifriends.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by aleksandrprendota on 27.08.17.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestClassHelper {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test(){

        long min = 60L;
        Date date = new Date();
        long time = date.getTime() - min;
        List<User> users = userRepository.findLastUsersMessage(1L, new Date(time));

        System.out.println(users);
    }
}

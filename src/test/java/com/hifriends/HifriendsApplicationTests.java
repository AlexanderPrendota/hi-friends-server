package com.hifriends;

import com.hifriends.mock.service.ChatServiceMockTest;
import com.hifriends.mock.service.MessageServiceMockTest;
import com.hifriends.mock.service.UserServiceMockTest;
import com.hifriends.model.User;
import com.hifriends.repository.UserRepository;
import com.hifriends.unit.ChatServiceTest;
import com.hifriends.unit.MessageServiceTest;
import com.hifriends.unit.UserServiceTest;
import com.hifriends.unit.dao.RepositoryTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ChatServiceMockTest.class,
        MessageServiceMockTest.class,
        UserServiceMockTest.class,
        UserServiceTest.class,
        ChatServiceTest.class,
        MessageServiceTest.class,
        RepositoryTest.class
})
public class HifriendsApplicationTests {

}

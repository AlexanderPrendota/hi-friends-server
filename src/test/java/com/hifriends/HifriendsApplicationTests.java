package com.hifriends;

import com.hifriends.mock.service.ChatServiceMockTest;
import com.hifriends.mock.service.MessageServiceMockTest;
import com.hifriends.mock.service.UserServiceMockTest;
import com.hifriends.unit.ChatServiceTest;
import com.hifriends.unit.MessageServiceTest;
import com.hifriends.unit.UserServiceTest;
import com.hifriends.unit.dao.RepositoryTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

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

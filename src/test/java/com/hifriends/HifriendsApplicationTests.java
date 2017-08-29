package com.hifriends;

import com.hifriends.mock.controllers.LoginControllerTest;
import com.hifriends.mock.controllers.MessageControllerTest;
import com.hifriends.mock.controllers.UserControllerTest;
import com.hifriends.mock.controllers.ViewControllerTest;
import com.hifriends.mock.service.ChatServiceMockTest;
import com.hifriends.mock.service.MessageServiceMockTest;
import com.hifriends.mock.service.UserServiceMockTest;
import com.hifriends.unit.service.ChatServiceTest;
import com.hifriends.unit.service.UserServiceTest;
import com.hifriends.unit.dao.RepositoryTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ChatServiceMockTest.class,
        MessageServiceMockTest.class,
        UserServiceMockTest.class,
        UserServiceTest.class,
        LoginControllerTest.class,
        MessageControllerTest.class,
        UserControllerTest.class,
        ViewControllerTest.class,
        ChatServiceTest.class,
        RepositoryTest.class
})
public class HifriendsApplicationTests {

}

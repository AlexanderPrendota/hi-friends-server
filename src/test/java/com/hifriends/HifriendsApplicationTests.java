package com.hifriends;

import com.hifriends.model.Chat;
import com.hifriends.model.User;
import com.hifriends.repository.ChatRepository;
import com.hifriends.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HifriendsApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ChatRepository chatRepository;

	@Test
	public void contextLoads() {
		List<User> user = userRepository.findByActiveIsTrue();
		Assert.assertTrue(user.size() > 0);
	}

	@Test
	public void testChat(){
		Chat chat = chatRepository.findChatByUsers(1L, 9999L);
		Assert.assertNotNull(chat);
	}

}

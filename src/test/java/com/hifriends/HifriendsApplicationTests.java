package com.hifriends;

import com.hifriends.model.User;
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

	@Test
	public void contextLoads() {
		List<User> user = userRepository.findByActiveIsTrue();
		Assert.assertTrue(user.size() > 0);
	}

}

package com.hifriends.mock.service;

import com.hifriends.model.User;
import com.hifriends.model.dto.UserDto;
import com.hifriends.repository.UserRepository;
import com.hifriends.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

/**
 * @author by aleksandrprendota on 26.08.17.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceMockTest {

    private User user;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        user = User.builder().email("S@s").id(1L).imagePath("/path").name("Sasha").build();
    }

    @Test
    public void getAllActiveUsers() throws Exception {
        when(userRepository.findByActiveIsTrueAndIdNot(1L)).thenReturn(new ArrayList<>());
        userService.getAllActiveUsers(user.getId());
        verify(userRepository).findByActiveIsTrueAndIdNot(user.getId());
    }

    @Test
    public void updateUserStatus() throws Exception {
        when(userRepository.findOne(1L)).thenReturn(user);
        userService.updateUserStatus(user.getId());
        verify(userRepository).findOne(user.getId());
    }

    @Test
    public void registrateUser() throws Exception {
        when(userRepository.findByEmail("S@s")).thenReturn(user);
        when(modelMapper.map(any(), any())).thenReturn(new UserDto());
        userService.registrateUser("Sasha", "S@s", "/path");
        verify(userRepository).findByEmail(user.getEmail());
        verify(modelMapper).map(any(), any());
    }
}

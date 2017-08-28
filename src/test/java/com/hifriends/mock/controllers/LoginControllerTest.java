package com.hifriends.mock.controllers;

import com.hifriends.controllers.LoginController;
import com.hifriends.model.dto.UserDto;
import com.hifriends.service.api.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import static org.mockito.Mockito.*;

/**
 * @author  aleksandrprendota on 28.08.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginController loginController;

    @Test
    public void loginTest(){
        when(userService.registrateUser(anyString(),anyString(),anyString())).thenReturn(new UserDto());
        loginController.login("name","email","avatar", mock(Model.class));
        verify(userService).registrateUser(anyString(),anyString(),anyString());
    }
}

package com.hifriends.mock.controllers;

import com.hifriends.controllers.UserController;
import com.hifriends.service.api.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

/**
 * @author aleksandrprendota on 28.08.17.
 */

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;


    @Test
    public void testGetActiveUsers(){
        when(userService.getAllActiveUsers(1L)).thenReturn(new ArrayList<>());
        userController.getActiveUsers(1L);
        verify(userService).getAllActiveUsers(1L);
    }

    @Test
    public void testUpdateUserStatus(){
        doNothing().when(userService).updateUserStatus(1L);
        userController.updateUserStatus(1L);
        verify(userService, times(1)).updateUserStatus(1L);

    }

    @Test
    public void testGetUserNotifications(){
        when(userService.getUsersIdsByNewMessages(1L)).thenReturn(new ArrayList<>());
        userController.userNotifications(1L);
        verify(userService).getUsersIdsByNewMessages(1L);
    }
}

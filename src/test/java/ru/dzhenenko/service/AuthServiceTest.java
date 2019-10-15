package ru.dzhenenko.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.dzhenenko.converter.UserModelToUserDtoConverter;
import ru.dzhenenko.dao.UserDao;
import ru.dzhenenko.dao.UserModel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @InjectMocks AuthService subj;

    @Mock UserDao userDao;
    @Mock DigestService digestService;
    @Mock UserModelToUserDtoConverter userDtoConverter;

    @Test
    public void auth_userNotFound() {
        when(digestService.hex("qwerty1234567890")).thenReturn("hex");
        when(userDao.findByEmailAndHash("i.dzhenenko@gmail.com", "hex")).thenReturn(null);

        UserDTO user = subj.auth("i.dzhenenko@gmail.com" ,"qwerty1234567890");

        assertNull(user);

        verify(digestService, times(1)).hex("qwerty1234567890");
        verify(userDao, times(1)).findByEmailAndHash("i.dzhenenko@gmail.com" ,"hex");
        verifyZeroInteractions(userDtoConverter);
    }
    @Test
    public void auth_userFound() {
        when(digestService.hex("qwerty1234567890")).thenReturn("hex");

        UserModel userModel = new UserModel();
        userModel.setId(1);
        userModel.setEmail("i.dzhenenko@gmail.com");
        userModel.setPassword("hex");
        when(userDao.findByEmailAndHash("i.dzhenenko@gmail.com", "hex")).thenReturn(userModel);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setEmail("i.dzhenenko@gmail.com");
        when(userDtoConverter.convert(userModel)).thenReturn(userDTO);

        UserDTO user = subj.auth("i.dzhenenko@gmail.com" ,"qwerty1234567890");

        assertNotNull(user);
        assertEquals(userDTO, user);

        verify(digestService, times(1)).hex("qwerty1234567890");
        verify(userDao, times(1)).findByEmailAndHash("i.dzhenenko@gmail.com" ,"hex");
        verify(userDtoConverter, times(1)).convert(userModel);
    }

    @Test
    public void registration() {
    }
}
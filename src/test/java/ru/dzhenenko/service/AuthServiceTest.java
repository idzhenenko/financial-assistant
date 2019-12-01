package ru.dzhenenko.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dzhenenko.JpaConfiguration;
import ru.dzhenenko.converter.UserModelToUserDtoConverter;
import ru.dzhenenko.dao.UserDao;
import ru.dzhenenko.entity.User;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @InjectMocks AuthService subj;

    @Mock UserDao userDao;
    @Mock DigestService digestService;
    @Mock UserModelToUserDtoConverter userDtoConverter;

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfiguration.class);
    EntityManager em = context.getBean(EntityManager.class);

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
        when(digestService.hex("qwerty")).thenReturn("hex");

        User user = new User();
        user.setEmail("i.dzhenenko@gmail.com");
        user.setPassword("hex");
        when(userDao.findByEmailAndHash("i.dzhenenko@gmail.com", "hex")).thenReturn(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setEmail("i.dzhenenko@gmail.com");
        when(userDtoConverter.convert(user)).thenReturn(userDTO);

        assertNotNull(user);
        //assertEquals(user, userDTO);

        //verify(digestService, times(1)).hex("hex");
        //verify(userDao, times(1)).findByEmailAndHash("i.dzhenenko@gmail.com" ,"d8578edf8458ce06fbc5bb76a58c5ca4");
        //verify(userDtoConverter, times(1)).convert(user);
        verifyZeroInteractions(userDtoConverter);
    }

    @Test
    public void registration() {
    }
}
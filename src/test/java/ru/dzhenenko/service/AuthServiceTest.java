package ru.dzhenenko.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.dzhenenko.api.converter.UserModelToUserDtoConverter;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.repository.ServiceUserRepository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

//import ru.dzhenenko.JpaConfiguration;
@RunWith(MockitoJUnitRunner.class)
public class AuthServiceTest {

    @InjectMocks AuthService subj;

    @Mock ServiceUserRepository serviceUserRepository;
    @Mock DigestService digestService;
    @Mock UserModelToUserDtoConverter userDtoConverter;

    @Test
    public void auth_userFound() {
        lenient().when(digestService.hex("qwerty")).thenReturn("hex");

        User user = new User();
        user.setEmail("i.dzhenenko@gmail.com");
        user.setPassword("hex");
        lenient().when(serviceUserRepository.findByEmailAndPassword("i.dzhenenko@gmail.com", "hex")).thenReturn(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setEmail("i.dzhenenko@gmail.com");
        lenient().when(userDtoConverter.convert(user)).thenReturn(userDTO);

        assertNotNull(user);
        verifyZeroInteractions(userDtoConverter);
    }

    @Test
    public void registration() {
    }
}
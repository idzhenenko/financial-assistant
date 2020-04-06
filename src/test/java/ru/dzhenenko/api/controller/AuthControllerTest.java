package ru.dzhenenko.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.dzhenenko.MockSecurityConfiguration;
import ru.dzhenenko.SecurityConfiguration;
import ru.dzhenenko.api.converter.ServiceUserToResponseConverter;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.repository.ServiceUserRepository;
import ru.dzhenenko.secuity.UserRole;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import({SecurityConfiguration.class, MockSecurityConfiguration.class})
@RunWith(SpringRunner.class)
class AuthControllerTest {

    @Autowired MockMvc mockMvc;
    @MockBean ServiceUserRepository serviceUserRepository;
    @MockBean AuthService authService;
    @SpyBean ServiceUserToResponseConverter converter;

    @BeforeEach
    void setUp() throws Exception{
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setFirstName("Ivan");
        user.setLastName("Dzhenenko");
        user.setPhone("+8800666999");
        user.setEmail("i.dzhenenko@gmail.com");
        when(authService.currentUser()).thenReturn(user);

    }

    @WithUserDetails(value = "i.dzhenenko@gmail.com", userDetailsServiceBeanName = "userDetailsService")
    @Test
    public void getUserInfo() throws Exception {
        mockMvc.perform(get("/api/get-user-info"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"firstName\": \"Ivan\",\n" +
                        "  \"lastName\": \"Dzhenenko\",\n" +
                        "  \"phone\": \"+8800666999\",\n" +
                        "  \"email\": \"i.dzhenenko@gmail.com\"\n" +
                        "}\n"));
    }
}
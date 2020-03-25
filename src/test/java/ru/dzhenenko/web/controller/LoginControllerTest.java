package ru.dzhenenko.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.dzhenenko.MockSecurityConfiguration;
import ru.dzhenenko.SecurityConfiguration;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.repository.ServiceUserRepository;
import ru.dzhenenko.secuity.UserRole;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginController.class)
@Import({SecurityConfiguration.class, MockSecurityConfiguration.class})
@RunWith(SpringRunner.class)
class LoginControllerTest {

    @Autowired MockMvc mockMvc;

    @MockBean ServiceUserRepository serviceUserRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getLogin() throws Exception {
        mockMvc.perform(get("login-form"))
                .andExpect(status().isOk())
                .andExpect(view().name("login-form"));
    }

    @WithUserDetails(value = "i.dzhenenko@gmail.com", userDetailsServiceBeanName = "userDetailsService")
    @Test
    void index() throws Exception {
        when(serviceUserRepository.getOne(1L)).thenReturn(
                new User()

                /*new User().setId(1L)
                            .setEmail("i.dzhenenko@gmail.com")
                            .setPassword("qwerty")
                            .setRoles(Collections.singleton(UserRole.USER))*/
        );

        mockMvc.perform(get("personal-area"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("id", 1L))
                .andExpect(model().attribute("name", "i.dzhenenko@gmail.com"))
                .andExpect(view().name("personal-area"));
    }

}
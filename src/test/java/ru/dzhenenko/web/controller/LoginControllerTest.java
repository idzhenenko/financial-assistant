package ru.dzhenenko.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.dzhenenko.MockSecurityConfiguration;
import ru.dzhenenko.SecurityConfiguration;
import ru.dzhenenko.repository.ServiceUserRepository;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;
import ru.dzhenenko.web.form.AddUserForm;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ContextConfiguration(locations = { "classpath:/META-INF/LoginControllerTest.class" })
//@WebMvcTest(value = LoginController.class, excludeAutoConfiguration = {SecurityConfiguration.class})
//@WebMvcTest(value = LoginController.class, excludeAutoConfiguration = {MockSecurityConfiguration.class})
@WebMvcTest(value = LoginController.class)
@Import({SecurityConfiguration.class, MockSecurityConfiguration.class})
@RunWith(SpringRunner.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
//@ActiveProfiles("test")
class LoginControllerTest {

    @Autowired MockMvc mockMvc;

    @MockBean ServiceUserRepository serviceUserRepository;

    @MockBean AuthService authService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void registration_pass() throws Exception {

        mockMvc.perform(post("/add-user")
                .flashAttr("form", new AddUserForm()
                        .setFirstName("Ivan")
                        .setLastName("Dzhenenko")
                        .setEmail("i.dzhenenko@gmail.com")
                        .setPhone("+8800666999")
                        .setPassword("$2y$12$1GkGr4hhAEX0exlONRfnYu5St31AuKf5xjIEXq7GWiO91iSLzKH2e")))
                .andExpect(view().name("addUser"));
    }

    @Test
    void getLogin() throws Exception {
        mockMvc.perform(get("/login-form"))
                .andExpect(status().isOk())
                .andExpect(view().name("login-form"));
    }

    @WithUserDetails(value = "i.dzhenenko@gmail.com", userDetailsServiceBeanName = "userDetailsService")
    @Test
    void index() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName("Ivan");
        userDTO.setLastName("Dzhenenko");
        userDTO.setEmail("i.dzhenenko@gmail.com");
        userDTO.setPhone("+8800666999");

        when(authService.currentUser()).thenReturn(userDTO);

        mockMvc.perform(get("/personal-area"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("id", 1L))
                .andExpect(model().attribute("name", "Ivan"))
                .andExpect(view().name("personal-area"));
    }
    @Test
    void getUser() throws Exception {
        mockMvc.perform(get("/add-user"))
                .andExpect(status().isOk())
                .andExpect(view().name("addUserGet"));
    }

}
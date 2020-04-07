package ru.dzhenenko.web.controller;

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
import ru.dzhenenko.service.AccountService;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;
import ru.dzhenenko.web.form.AddAccountForm;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddAccountsController.class)
@Import({SecurityConfiguration.class, MockSecurityConfiguration.class})
@RunWith(SpringRunner.class)
class AddAccountsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthService authService;

    @MockBean
    AccountService accountService;

    @WithUserDetails(value = "i.dzhenenko@gmail.com", userDetailsServiceBeanName = "userDetailsService")
    @Test
    void getAccount() throws Exception {
        mockMvc.perform(get("/add-account"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("form", new AddAccountForm()))
                .andExpect(view().name("addAccountGet"));

    }

    @WithUserDetails(value = "i.dzhenenko@gmail.com", userDetailsServiceBeanName = "userDetailsService")
    @Test
    void postAccount() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName("Ivan");
        userDTO.setLastName("Dzhenenko");
        userDTO.setEmail("i.dzhenenko@gmail.com");
        userDTO.setPhone("+8800666999");
        when(authService.currentUser()).thenReturn(userDTO);

        mockMvc.perform(post("/add-account")
                .flashAttr("form", new AddAccountForm()))
                .andExpect(status().isOk())
                .andExpect(view().name("addNewAccount"));
    }
}
package ru.dzhenenko.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.dzhenenko.MockSecurityConfiguration;
import ru.dzhenenko.SecurityConfiguration;
import ru.dzhenenko.service.AccountDTO;
import ru.dzhenenko.service.AccountService;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import java.sql.SQLException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
@Import({SecurityConfiguration.class, MockSecurityConfiguration.class})
@RunWith(SpringRunner.class)
class AccountControllerTest {

    @Autowired MockMvc mockMvc;

    @MockBean AuthService authService;

    @MockBean AccountService accountService;

    @BeforeEach
    void setUp() throws SQLException {
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setFirstName("Ivan");
        user.setLastName("Dzhenenko");
        user.setPhone("+8800666999");
        user.setEmail("i.dzhenenko@gmail.com");
        when(authService.currentUser()).thenReturn(user);

        AccountDTO account = new AccountDTO();
        account.setId(1L);
        account.setName("Bank");
        account.setBalance(1000000);
        account.setUserId(1L);
        when(accountService.viewAccount(1L))
                .thenReturn(Collections.singletonList(account));
    }

    @WithUserDetails(value = "i.dzhenenko@gmail.com", userDetailsServiceBeanName = "userDetailsService")
    @Test
    void viewListAccount() throws Exception {
        mockMvc.perform(post("/api/view-account"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\n" +
                        "  \"id\": 1,\n" +
                        "  \"name\": \"Bank\",\n" +
                        "  \"balance\": 1000000,\n" +
                        "  \"userId\": 1\n" +
                        "}]"));
    }
}

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
import ru.dzhenenko.service.*;
import ru.dzhenenko.web.form.AddTypeAccountForm;
import ru.dzhenenko.web.form.InsertNewTransactionForm;

import java.sql.SQLException;
import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InsertNewTransactionController.class)
@Import({SecurityConfiguration.class, MockSecurityConfiguration.class})
@RunWith(SpringRunner.class)
class InsertNewTransactionControllerTest {

    @Autowired MockMvc mockMvc;

    @MockBean TransactionService transactionService;

    @MockBean AuthService authService;

    @WithUserDetails(value = "i.dzhenenko@gmail.com", userDetailsServiceBeanName = "userDetailsService")
    @Test
    void getTransactionNew() throws Exception {
            when(authService.currentUser()).thenReturn(new UserDTO());

            mockMvc.perform(get("/insert-new-transaction"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("form", new InsertNewTransactionForm()))
                    .andExpect(view().name("addNewTransactionGet"));
    }

    @WithUserDetails(value = "i.dzhenenko@gmail.com", userDetailsServiceBeanName = "userDetailsService")
    @Test
    void postAccount() throws Exception {
        when(authService.currentUser()).thenReturn(new UserDTO());

        mockMvc.perform(post("/insert-new-transaction"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("form" , new InsertNewTransactionForm()))
                .andExpect(view().name("addNewTransaction"));

    }
}
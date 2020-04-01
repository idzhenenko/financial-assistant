package ru.dzhenenko.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.dzhenenko.MockSecurityConfiguration;
import ru.dzhenenko.SecurityConfiguration;
import ru.dzhenenko.repository.ServiceUserRepository;
import ru.dzhenenko.service.AccountDTO;
import ru.dzhenenko.service.AccountService;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;
import ru.dzhenenko.web.form.AddAccountForm;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AddAccountsController.class)
@Import({SecurityConfiguration.class, MockSecurityConfiguration.class})
@RunWith(SpringRunner.class)
class AddAccountsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ServiceUserRepository serviceUserRepository;

    @MockBean AuthService authService;

    @MockBean AccountService accountService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getAccount() throws Exception {
        mockMvc.perform(get("/add-account"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("form", new AddAccountForm()))
                .andExpect(view().name("addAccountGet"));

    }

    @Test
    void postAccount() throws Exception {
        /*UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName("Ivan");
        userDTO.setLastName("Dzhenenko");
        userDTO.setEmail("i.dzhenenko@gmail.com");
        userDTO.setPhone("+8800666999");

        when(authService.currentUser()).thenReturn(userDTO);*/

        AccountDTO account = new AccountDTO();
        account.setUserId(1L);
        account.setBalance(1000);
        account.setName("Bank");

        mockMvc.perform(get("/add-account"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("id", 1L))
                .andExpect(model().attribute("name", "Bank"))
                .andExpect(view().name("addNewAccount"));
    }
}
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
import ru.dzhenenko.web.form.AddAccountForm;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ViewAccountTypesController.class)
@Import({SecurityConfiguration.class, MockSecurityConfiguration.class})
@RunWith(SpringRunner.class)
class ViewAccountTypesControllerTest {

    @Autowired MockMvc mockMvc;

    @MockBean AuthService authService;

    @MockBean AccountTypeService accountTypeService;

    @WithUserDetails(value = "i.dzhenenko@gmail.com", userDetailsServiceBeanName = "userDetailsService")
    @Test
    void getTypesAccount() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName("Ivan");
        userDTO.setLastName("Dzhenenko");
        userDTO.setEmail("i.dzhenenko@gmail.com");
        userDTO.setPhone("+8800666999");
        when(authService.currentUser()).thenReturn(userDTO);

        AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
        accountTypeDTO.setId(1L);
        accountTypeDTO.setName("Name");
        when(accountTypeService.viewTypeAccount(1L))
                .thenReturn(Collections.singletonList(accountTypeDTO));

        mockMvc.perform(get("/view-type-account"))
                .andExpect(status().isOk())
                .andExpect(view().name("viewTypeAccountGet"));
    }
}
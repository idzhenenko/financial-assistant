package ru.dzhenenko.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.dzhenenko.api.json.AddTypeTransactionRequest;
import ru.dzhenenko.service.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddTypeTransactionController.class)
@Import({SecurityConfiguration.class, MockSecurityConfiguration.class})
@RunWith(SpringRunner.class)
class AddTypeTransactionControllerTest {

    @Autowired MockMvc mockMvc;
    @MockBean AuthService authService;
    @MockBean AccountTypeService accountTypeService;

    @WithUserDetails(value = "i.dzhenenko@gmail.com", userDetailsServiceBeanName = "userDetailsService")
    @Test
    void addTypeAccount() throws Exception {
        UserDTO user = new UserDTO();
        user.setId(1L);
        user.setFirstName("Ivan");
        user.setLastName("Dzhenenko");
        user.setPhone("+8800666999");
        user.setEmail("i.dzhenenko@gmail.com");
        when(authService.currentUser()).thenReturn(user);

        AddTypeTransactionRequest request = new AddTypeTransactionRequest();
        request.setId(1L);
        request.setName("Расход");
        AccountTypeDTO accountType = new AccountTypeDTO();
        accountType.setId(1L);
        accountType.setName("Расход");
        when(accountTypeService.createTypeAccount("Расход", 1L))
                .thenReturn(accountType);

        mockMvc.perform(post("/api/add-type-account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "  \"id\": 1,\n" +
                        "  \"name\": \"Расход\"\n" +
                        "}"));
    }
}
package ru.dzhenenko.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.dzhenenko.api.converter.ServiceUserToResponseConverter;
import ru.dzhenenko.api.json.RegistrationRequest;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegistrationController.class)
@Import({SecurityConfiguration.class, MockSecurityConfiguration.class})
@RunWith(SpringRunner.class)
class RegistrationControllerTest {

    @Autowired MockMvc mockMvc;
    @MockBean AuthService authService;
    @MockBean ServiceUserToResponseConverter converter;

        @WithUserDetails(value = "i.dzhenenko@gmail.com", userDetailsServiceBeanName = "userDetailsService")
        @Test
        public void registration() throws Exception {
            RegistrationRequest request = new RegistrationRequest();;
            request.setFirstName("Ivan");
            request.setLastName("Dzhenenko");
            request.setPhone("+8800666999");
            request.setEmail("i.dzhenenko@gmail.com");
            request.setPassword("$2y$12$1GkGr4hhAEX0exlONRfnYu5St31AuKf5xjIEXq7GWiO91iSLzKH2e");

            UserDTO user = new UserDTO();
            user.setId(1L);
            user.setFirstName("Ivan");
            user.setLastName("Dzhenenko");
            user.setPhone("+8800666999");
            user.setEmail("i.dzhenenko@gmail.com");

            when(authService.registration(request.getFirstName(), request.getLastName(),
                    request.getPhone(), request.getEmail(), request.getPassword())).thenReturn(user);

            mockMvc.perform(post("/api/registration")
                    .contentType("application/json")
                    .content(new ObjectMapper().writeValueAsString(request)))
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
package ru.dzhenenko.web.controller;

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
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.web.form.AddUserForm;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@RunWith(SpringRunner.class)
@WebMvcTest(AddUserController.class)
@Import({SecurityConfiguration.class, MockSecurityConfiguration.class})
class AddUserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthService authService;

    @Test
    void getUser() throws Exception {
        mockMvc.perform(get("/add-user"))
                .andExpect(status().isOk())
                .andExpect(view().name("addUserGet"));
    }

    @Test
    void postUser() throws Exception {
        mockMvc.perform(post("/add-user")
                .flashAttr("form", new AddUserForm()
                        .setFirstName("Ivan")
                        .setLastName("Dzhenenko")
                        .setEmail("i.dzhenenko@gmail.com")
                        .setPhone("+8800666999")
                        .setPassword("$2y$12$1GkGr4hhAEX0exlONRfnYu5St31AuKf5xjIEXq7GWiO91iSLzKH2e")))
                .andExpect(view().name("addUser"));
    }
}

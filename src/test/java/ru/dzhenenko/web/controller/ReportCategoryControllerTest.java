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
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.ReportByCategoryDTO;
import ru.dzhenenko.service.ReportByCategoryService;
import ru.dzhenenko.service.UserDTO;
import ru.dzhenenko.web.form.AddTypeAccountForm;
import ru.dzhenenko.web.form.ReportForm;

import java.sql.SQLException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReportCategoryController.class)
@Import({SecurityConfiguration.class, MockSecurityConfiguration.class})
@RunWith(SpringRunner.class)
class ReportCategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    AuthService authService;

    @MockBean ReportForm form;

    @MockBean ReportByCategoryService service;

    @WithUserDetails(value = "i.dzhenenko@gmail.com", userDetailsServiceBeanName = "userDetailsService")
    @Test
    void getReport() throws Exception {
        mockMvc.perform(get("/report-account-category"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("form", new ReportForm()))
                .andExpect(view().name("categoryReportGet"));
    }

    @WithUserDetails(value = "i.dzhenenko@gmail.com", userDetailsServiceBeanName = "userDetailsService")
    @Test
    void postReport() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setFirstName("Ivan");
        userDTO.setLastName("Dzhenenko");
        userDTO.setEmail("i.dzhenenko@gmail.com");
        userDTO.setPhone("+8800666999");
        when(authService.currentUser()).thenReturn(userDTO);

        ReportByCategoryDTO report = new ReportByCategoryDTO();
        report.setName("Самолет");
        report.setAmount(1_000_000L);
        when(service.viewReportCategory(1L, "2020-09-10", "2020-10-10"))
                .thenReturn(Collections.singletonList(report));

        ReportForm form = new ReportForm();
        form.setStartDay("2020-09-10");
        form.setEndDay("2020-10-10");

        mockMvc.perform(post("/report-account-category")
                .flashAttr("form", form))
                //.andExpect(model().attribute("report",report ))
                .andExpect(model().attribute("startDay", "2020-09-10"))
                .andExpect(model().attribute("endDay", "2020-10-10"))
                .andExpect(status().isOk())
                .andExpect(view().name("categoryReport"));
    }
}
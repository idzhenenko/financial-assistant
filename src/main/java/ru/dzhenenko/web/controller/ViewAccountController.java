package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dzhenenko.service.AccountService;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import java.sql.SQLException;

@RequiredArgsConstructor
@Controller
public class ViewAccountController {
    private final AccountService accountService;
    private final AuthService authService;

    @GetMapping("/view-account")
    public String getAccount(Model model) throws SQLException {
        UserDTO userDTO = authService.currentUser();
        model.addAttribute("accounts", accountService.viewAccount(userDTO.getId()));
        return "viewAccount";
    }
}

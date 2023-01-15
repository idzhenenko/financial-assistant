package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dzhenenko.service.AccountTypeService;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import java.sql.SQLException;

@RequiredArgsConstructor
@Controller
public class ViewAccountTypesController {
    private final AccountTypeService accountTypeService;
    private final AuthService authService;

    @GetMapping("/view-type-account")
    public String getTypesAccount(Model model) {
        UserDTO userDTO = authService.currentUser();
        model.addAttribute("accountsType", accountTypeService.viewTypeAccount(userDTO.getId()));
        return "viewTypeAccountGet";
    }
}
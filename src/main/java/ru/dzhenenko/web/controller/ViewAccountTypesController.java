package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dzhenenko.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ViewAccountTypesController {
    private final AccountTypeService accountTypeService;
    private final AuthService authService;

    @GetMapping("/view-type-account")
    public String getTypesAccount(Model model, HttpServletRequest request) throws SQLException {
        UserDTO userDTO = authService.currentUser();

        model.addAttribute("accountsType", accountTypeService.viewTypeAccount(userDTO.getId()));

        return "viewTypeAccountGet";
    }
}

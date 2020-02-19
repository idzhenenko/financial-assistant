package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dzhenenko.service.AccountService;
import ru.dzhenenko.service.AccountTypeDTO;
import ru.dzhenenko.service.AccountTypeService;
import ru.dzhenenko.service.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ViewAccountTypesController {
    private final AccountTypeService accountTypeService;
    private final AccountService accountService;

    @GetMapping("/viewTypeAccount")
    public String getTypesAccount(Model model, HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        model.addAttribute("accountsType", accountTypeService.viewTypeAccount(userId));

        return "viewTypeAccountGet";
    }
}

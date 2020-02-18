package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dzhenenko.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@RequiredArgsConstructor
@Controller
public class ViewAccountController {
    private final AccountService accountService;

    @GetMapping("/viewAccount")
    public String getAccount(Model model, HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");

        model.addAttribute("accounts", accountService.viewAccount(userId));

        return "viewAccount";
    }
}

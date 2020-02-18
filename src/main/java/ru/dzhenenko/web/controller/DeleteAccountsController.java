package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dzhenenko.service.*;
import ru.dzhenenko.web.form.AddTypeAccountForm;
import ru.dzhenenko.web.form.DeleteAccountForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;

@RequiredArgsConstructor
@Controller
public class DeleteAccountsController {
    private final AccountService accountService;
    private final AuthService authService;

    @GetMapping("delete-account")
    public String getDeleteAccount(Model model) throws SQLException {

        model.addAttribute("form", new DeleteAccountForm());

        return "deleteAccountGet";

    }

    @PostMapping("delete-account")
    public String postDeleteAccount(@ModelAttribute("form") @Valid AddTypeAccountForm form, BindingResult result, Model model,
                                    HttpServletRequest request) throws SQLException {
        HttpSession session = request.getSession();
        Long idUser = (Long) session.getAttribute("userId");
        UserDTO userDTO = authService.getUserById(idUser);

        if (idUser == null) {
            return "redirect:/login";
        }

        AccountDTO account = accountService.removeAccount(form.getId());

        model.addAttribute("name", account.getName())
                .addAttribute("balance", account.getBalance())
                .addAttribute("id", account.getId());

        model.addAttribute("form", new DeleteAccountForm());
        return "deleteAccountPost";
    }
}

package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dzhenenko.service.AccountDTO;
import ru.dzhenenko.service.AccountService;
import ru.dzhenenko.web.form.DeleteAccountForm;

import javax.validation.Valid;
import java.sql.SQLException;

@RequiredArgsConstructor
@Controller
public class DeleteAccountsController {
    private final AccountService accountService;

    @GetMapping("/delete-account")
    public String getDeleteAccount(Model model) throws SQLException {
        model.addAttribute("form", new DeleteAccountForm());
        return "deleteAccountGet";
    }

    @PostMapping("/delete-account")
    public String postDeleteAccount(@ModelAttribute("form") @Valid DeleteAccountForm form, Model model) throws SQLException {
        AccountDTO account = accountService.removeAccount(form.getId());
        model.addAttribute("name", account.getName())
                .addAttribute("balance", account.getBalance())
                .addAttribute("id", account.getId());
        model.addAttribute("form", new DeleteAccountForm());
        return "deleteAccountPost";
    }
}

package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dzhenenko.service.AccountDTO;
import ru.dzhenenko.service.AccountService;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;
import ru.dzhenenko.web.form.AddAccountForm;

import javax.validation.Valid;
import java.sql.SQLException;

@RequiredArgsConstructor
@Controller
public class AddAccountsController {
    private final AccountService accountService;
    private final AuthService authService;

    @GetMapping("/add-account")
    public String getAccount(Model model) {

        model.addAttribute("form", new AddAccountForm());

        return "addAccountGet";
    }

    @PostMapping("/add-account")
    public String postAccount(@ModelAttribute("form") @Valid AddAccountForm form, BindingResult result, Model model) throws SQLException {
        if (!result.hasErrors()) {

            UserDTO userId = authService.currentUser();

            UserDTO userDTO = authService.getUserById(userId.getId());

            AccountDTO account = accountService.createAccount(form.getName(), form.getBalance(), userDTO.getId());

            model.addAttribute("name", account.getName());
            model.addAttribute("balance", account.getBalance());
            model.addAttribute("id", account.getId());

            return "addNewAccount";
        }
        model.addAttribute("form", form);

        return "addNewAccount";
    }
}

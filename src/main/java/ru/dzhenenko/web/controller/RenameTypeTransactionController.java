package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dzhenenko.service.AccountTypeDTO;
import ru.dzhenenko.service.AccountTypeService;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;
import ru.dzhenenko.web.form.AddTypeAccountForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;

@RequiredArgsConstructor
@Controller
public class RenameTypeTransactionController {
    private final AccountTypeService accountTypeService;
    private final AuthService authService;

    @GetMapping("/rename-type-account")
    public String getAccount(Model model) {

        model.addAttribute("form", new AddTypeAccountForm());

        return "renameTypeAccountGet";

    }

    @PostMapping("/rename-type-account")
    public String postAccount(@ModelAttribute("form") @Valid AddTypeAccountForm form, BindingResult result, Model model,
                              HttpServletRequest request) throws SQLException {
        if (!result.hasErrors()) {

            AccountTypeDTO accountTypeDTO = accountTypeService.editingAccountType(form.getName(), form.getId());


            model.addAttribute("name", accountTypeDTO.getName());
            model.addAttribute("id", accountTypeDTO.getId());

            return "renameTypeAccount";
        }
        model.addAttribute("form", form);

        return "renameTypeAccount";
    }
}

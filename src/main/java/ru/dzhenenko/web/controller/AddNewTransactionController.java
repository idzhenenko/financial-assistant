package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dzhenenko.service.*;
import ru.dzhenenko.web.form.AddTypeAccountForm;
import ru.dzhenenko.web.form.AddUserForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;

@RequiredArgsConstructor
@Controller
public class AddNewTransactionController {
    private final AccountTypeService accountTypeService;
    private final AuthService authService;

    @GetMapping("/add-type-account")
    public String getAccount(Model model) {

        model.addAttribute("form", new AddTypeAccountForm());

        return "addTypeAccountGet";

    }

    @PostMapping("/add-type-account")
    public String postAccount(@ModelAttribute("form") @Valid AddTypeAccountForm form, BindingResult result, Model model,
                              HttpServletRequest request) throws SQLException {
        if (!result.hasErrors()) {
            UserDTO userDTO = authService.currentUser();

            AccountTypeDTO accountTypeDTO = accountTypeService.createTypeAccount(form.getName(), userDTO.getId());

            model.addAttribute("name", accountTypeDTO.getName());
            model.addAttribute("id", accountTypeDTO.getId());

            return "addNewTypeAccount";
        }
        model.addAttribute("form", form);

        return "addNewTypeAccount";
    }

}

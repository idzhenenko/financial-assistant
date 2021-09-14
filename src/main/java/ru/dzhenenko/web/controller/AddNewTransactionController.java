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

import javax.validation.Valid;
import java.sql.SQLException;

@RequiredArgsConstructor
@Controller
public class AddNewTransactionController {
    private final AccountTypeService accountTypeService;
    private final AuthService authService;

    @GetMapping("/add-type-account")
    public String getTypeAccount(Model model) {
        model.addAttribute("form", new AddTypeAccountForm());
        return "addTypeAccountGet";
    }

    @PostMapping("/add-type-account")
    public String postTypeAccount(@ModelAttribute("form") @Valid AddTypeAccountForm form,
                                  BindingResult result, Model model) throws SQLException {
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

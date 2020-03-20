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
import ru.dzhenenko.web.form.DeleteAccountForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;

@RequiredArgsConstructor
@Controller
public class DeleteTypesTransactionController {
    private final AccountTypeService accountTypeService;
    private final AuthService authService;

    @GetMapping("delete-type-account")
    public String getDeleteTypeAccount(Model model) throws SQLException {

        model.addAttribute("form", new AddTypeAccountForm());

        return "deleteTypeAccountGet";
    }

    @PostMapping("delete-type-account")
    public String postDeleteTypeAccount(@ModelAttribute("form") @Valid AddTypeAccountForm form, BindingResult result, Model model,
                                        HttpServletRequest request) throws SQLException {

        UserDTO idUser = authService.currentUser();

        if (idUser == null) {
            return "redirect:/login";
        }

        AccountTypeDTO accountTypeDTO = accountTypeService.removeAccountType(form.getId());

        model.addAttribute("name", accountTypeDTO.getName())
                .addAttribute("id", accountTypeDTO.getId());

        model.addAttribute("form", new AddTypeAccountForm());
        return "deleteTypeAccountPost";
    }
}

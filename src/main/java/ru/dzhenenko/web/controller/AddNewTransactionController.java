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

    @GetMapping("/addTypeAccount")
    public String getAccount(Model model) {

        model.addAttribute("form", new AddTypeAccountForm());

        return "addTypeAccountGet";

    }

    @PostMapping("/addTypeAccount")
    public String postAccount(@ModelAttribute("form") @Valid AddTypeAccountForm form, BindingResult result, Model model,
                              HttpServletRequest request) throws SQLException {
        if (!result.hasErrors()) {
            HttpSession session = request.getSession();
            Long userId = (Long) session.getAttribute("userId");

            AccountTypeDTO accountTypeDTO = accountTypeService.createTypeAccount(form.getName(), userId);


            model.addAttribute("name", accountTypeDTO.getName());
            model.addAttribute("id", accountTypeDTO.getId());

            session = request.getSession();
            session.setAttribute("userId", accountTypeDTO.getId());

            return "addNewTypeAccount";
        }
        model.addAttribute("form", form);

        return "addNewTypeAccount";
    }

}

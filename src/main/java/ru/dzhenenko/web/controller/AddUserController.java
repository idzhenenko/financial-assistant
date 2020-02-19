package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;
import ru.dzhenenko.web.form.AddUserForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class AddUserController {
    private final AuthService authService;

    @GetMapping("/addUser")
    public String getUser(Model model) {

        model.addAttribute("form", new AddUserForm());

        return "addUserGet";
    }

    @PostMapping("/addUser")
    public String postUser(@ModelAttribute("form") @Valid AddUserForm form, BindingResult result, Model model,
                           HttpServletRequest request) {
        if (!result.hasErrors()) {

            UserDTO user = authService.registration(
                    form.getFirstName(),
                    form.getLastName(),
                    form.getPhone(),
                    form.getEmail(),
                    form.getPassword());

            model.addAttribute("name", user.getFirstName());

            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getId());
            }

            return "addUsеrPost";
        }
        model.addAttribute("form", form);

        return "addUsеrPost";
    }
}

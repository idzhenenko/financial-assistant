package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;
import ru.dzhenenko.web.form.AddUserForm;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class AddUserController {
    private final AuthService authService;

    @GetMapping("/add-user")
    public String getUser(Model model) {

        model.addAttribute("form", new AddUserForm());

        return "addUserGet";
    }

    @PostMapping("/add-user")
    public String postUser(@ModelAttribute("form") @Valid AddUserForm form, BindingResult result, Model model) {
        if (!result.hasErrors()) {

            UserDTO user = authService.registration(
                    form.getFirstName(),
                    form.getLastName(),
                    form.getPhone(),
                    form.getEmail(),
                    form.getPassword());

            model.addAttribute("name", user.getFirstName())
            .addAttribute("lastName", user.getLastName())
            .addAttribute("phone", user.getPhone())
            .addAttribute("email", user.getEmail())
            .addAttribute("idUser", user.getId());

            return "addUsеrPost";
        }
        model.addAttribute("form", form);

        return "addUsеrPost";
    }
}

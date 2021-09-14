package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
    public String getUser() {
        return "addUserGet";
    }

    @PostMapping("/add-user")
    public String postUser(@ModelAttribute("form") @Valid AddUserForm form) {
        UserDTO user = authService.registration(
                form.getFirstName(),
                form.getLastName(),
                form.getPhone(),
                form.getEmail(),
                form.getPassword());
        if (user != null) {
            return "redirect:/login-form";
        }
        return "addUser";
    }
}

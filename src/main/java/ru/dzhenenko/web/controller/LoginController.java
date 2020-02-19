package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;
import ru.dzhenenko.web.form.LoginForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class LoginController {
    private final AuthService authService;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        UserDTO user = authService.getUserById(userId);
        if (user == null) {
            session.removeAttribute("userId");
            return "redirect:/login";
        }
        model.addAttribute("name", user.getFirstName())
                .addAttribute("lastName", user.getLastName())
                .addAttribute("email", user.getEmail())
                .addAttribute("id", user.getId())
        ;
        return "index";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {

        model.addAttribute("form", new LoginForm());

        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("form") @Valid LoginForm form, BindingResult result, Model model, HttpServletRequest request) {

        if (!result.hasErrors()) {

            UserDTO user = authService.auth(form.getEmail(), form.getPassword());

            if (user != null) {

                HttpSession session = request.getSession();
                session.setAttribute("userId", user.getId());

                return "redirect:/";

            }

            result.addError(new FieldError("form", "email", "Неверный логин или пароль!"));
        }
        model.addAttribute("form", form);

        return "login";
    }
}

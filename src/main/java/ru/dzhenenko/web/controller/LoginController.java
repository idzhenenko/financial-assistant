package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.repository.ServiceUserRepository;
import ru.dzhenenko.secuity.CustomUserDetails;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class LoginController {
    private final AuthService authService;
    private final ServiceUserRepository serviceUserRepository;

    @GetMapping("/personal-area")
    public String index(Model model) {
        User user = currentUser();

        model.addAttribute("id", user.getId())
                .addAttribute("name", user.getFirstName());

        return "personal-area";
    }

    @GetMapping("/login-form")
    public String getLogin() {
         return "login-form";
    }

    private User currentUser() {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return serviceUserRepository.getOne(user.getId());
    }
}

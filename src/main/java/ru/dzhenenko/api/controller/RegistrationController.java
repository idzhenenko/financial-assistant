package ru.dzhenenko.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dzhenenko.api.converter.ServiceUserToResponseConverter;
import ru.dzhenenko.api.json.AuthRequest;
import ru.dzhenenko.api.json.AuthResponse;
import ru.dzhenenko.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RegistrationController {
    private final ServiceUserToResponseConverter converter;

    @PostMapping("/registration")
    public @ResponseBody
    ResponseEntity<AuthResponse> registration(@RequestBody @Valid AuthRequest authRequest, User user,
                                              HttpServletRequest httpServletRequest) {

        AuthResponse authResponse = converter.convert(user);

        if (authResponse.getId() == null) {
            return status(HttpStatus.UNAUTHORIZED).build();
        }

        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("userId", user.getId());
        return ok(converter.convert(user));
    }
}
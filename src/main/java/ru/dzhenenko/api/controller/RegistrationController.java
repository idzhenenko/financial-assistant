package ru.dzhenenko.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dzhenenko.api.converter.ServiceUserToResponseConverter;
import ru.dzhenenko.api.json.AuthResponse;
import ru.dzhenenko.api.json.RegistrationRequest;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RegistrationController {
    private final ServiceUserToResponseConverter converter;
    private final AuthService authService;

    @PostMapping("/registration")
    public @ResponseBody
    ResponseEntity<AuthResponse> registration(@RequestBody @Valid RegistrationRequest request) {

        UserDTO userDTO = authService.registration(request.getFirstName(),
                request.getLastName(), request.getPhone(),
                request.getEmail(), request.getPassword());

        if (userDTO == null) {
            return status(HttpStatus.UNAUTHORIZED).build();
        }
        return ok(new AuthResponse(userDTO.getId(), userDTO.getFirstName(),
                userDTO.getLastName(), userDTO.getPhone(), userDTO.getEmail()));
    }
}
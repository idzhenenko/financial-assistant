package ru.dzhenenko.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.dzhenenko.api.converter.ServiceUserToResponseConverter;
import ru.dzhenenko.api.json.AuthResponse;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.repository.ServiceUserRepository;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {
    private final ServiceUserRepository serviceUserRepository;
    private final ServiceUserToResponseConverter converter;
    private final AuthService authService;

    @GetMapping("/get-user-info")
    public @ResponseBody
    ResponseEntity<AuthResponse> getUserInfo() {

        UserDTO userId = authService.currentUser();
        if (userId == null) {
            return status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = serviceUserRepository.getOne(userId.getId());
        if (user == null) {
            return status(HttpStatus.UNAUTHORIZED).build();
        }
        return ok(converter.convert(user));
    }
}
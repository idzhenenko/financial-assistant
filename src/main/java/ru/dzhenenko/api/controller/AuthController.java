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
import ru.dzhenenko.repository.ServiceUserRepository;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/get-user-info")
    public @ResponseBody
    ResponseEntity<AuthResponse> getUserInfo() {
        UserDTO user = authService.currentUser();
        if (user == null) {
            return status(HttpStatus.UNAUTHORIZED).build();
        }
        return ok(new AuthResponse(user.getId(), user.getFirstName(),
                user.getLastName(), user.getPhone(), user.getEmail()));
    }
}
package ru.dzhenenko.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.json.AuthRequest;
import ru.dzhenenko.json.AuthResponse;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

@Service("/login")
@RequiredArgsConstructor
public class AuthController implements Controller<AuthRequest, AuthResponse> {
    private final AuthService authService;

    @Override
    public AuthResponse handle(AuthRequest request) {
        UserDTO userDTO = authService.auth(request.getEmail(), request.getPassword());
        if (userDTO != null) {
            return new AuthResponse(userDTO.getId(), userDTO.getEmail(), userDTO.getFirstName());
        }
        return null;
    }

    @Override
    public Class<AuthRequest> getRequestClass() {
        return AuthRequest.class;
    }
}


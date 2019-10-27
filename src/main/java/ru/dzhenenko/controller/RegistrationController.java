package ru.dzhenenko.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.json.*;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

@Service("/registration")
@RequiredArgsConstructor
public class RegistrationController implements SecureController<RegistrationRequest, AuthResponse> {
    private final AuthService authService;

    @Override
    public AuthResponse handle(RegistrationRequest request, Long userId) throws Exception {
        UserDTO user = authService.registration(request.getFirstName(), request.getLastName(), request.getPhone(), request.getEmail(), request.getPassword());
        if (user != null) {
            return new AuthResponse(user.getId(), user.getFirstName(),user.getEmail());
        }
        return null;
    }
    @Override
    public Class<RegistrationRequest> getRequestClass() {
        return RegistrationRequest.class;
    }
}
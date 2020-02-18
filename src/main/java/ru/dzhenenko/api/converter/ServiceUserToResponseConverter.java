package ru.dzhenenko.api.converter;

import org.springframework.stereotype.Component;
import ru.dzhenenko.api.json.AuthResponse;
import ru.dzhenenko.entity.User;

@Component
public class ServiceUserToResponseConverter implements Converter<User, AuthResponse> {
    @Override
    public AuthResponse convert(User user) {
        return new AuthResponse(user.getId(), user.getEmail(), user.getFirstName());
    }
}


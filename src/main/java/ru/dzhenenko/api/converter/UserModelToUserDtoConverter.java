package ru.dzhenenko.api.converter;

import org.springframework.stereotype.Service;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.service.UserDTO;

@Service
public class UserModelToUserDtoConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User source) {
        UserDTO user = new UserDTO();
        user.setId(source.getId());
        user.setEmail(source.getEmail());
        user.setFirstName(source.getFirstName());
        user.setLastName(source.getLastName());
        return user;
    }
}
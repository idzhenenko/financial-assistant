package ru.dzhenenko.converter;

import org.springframework.stereotype.Service;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.service.UserDTO;

@Service
public class UserModelToUserDtoConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User source) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(source.getId());
        userDTO.setEmail(source.getEmail());
        userDTO.setFirstName(source.getFirstName());
        userDTO.setLastName(source.getLastName());
        return userDTO;
    }
}
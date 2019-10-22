package ru.dzhenenko.converter;

import org.springframework.stereotype.Service;
import ru.dzhenenko.dao.UserModel;
import ru.dzhenenko.service.UserDTO;

@Service
public class UserModelToUserDtoConverter implements Converter<UserModel, UserDTO> {
    @Override
    public UserDTO convert(UserModel source) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(source.getId());
        userDTO.setEmail(source.getEmail());
        userDTO.setFirstName(source.getFirstName());
        userDTO.setLastName(source.getLastName());
        return userDTO;
    }
}
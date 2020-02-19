package ru.dzhenenko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.api.converter.Converter;
import ru.dzhenenko.dao.UserDao;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.repository.ServiceUserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserDao userDao;
    private final DigestService digestService;
    private final Converter<User, UserDTO> userDtoConverter;
    private final ServiceUserRepository serviceUserRepository;

    public UserDTO getUserById(Long userId) {
        User user = serviceUserRepository.getOne(userId);
        if (user == null) {
            return null;
        }
        return userDtoConverter.convert(user);
    }

    public UserDTO auth(String email, String password) {
        UserDTO userDTO = null;
        User user = serviceUserRepository.findByEmailAndPassword(email, digestService.hex(password));

        if (user != null) {
            userDTO = userDtoConverter.convert(user);
        }
        return userDTO;
    }


    public UserDTO registration(String firstName, String lastName, String phone, String email, String password) {
        String hash = digestService.hex(password);

        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(hash);

        User userSave = serviceUserRepository.save(user);

        return userDtoConverter.convert(userSave);
    }

}
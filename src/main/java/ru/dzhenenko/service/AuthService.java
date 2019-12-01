package ru.dzhenenko.service;

import org.springframework.stereotype.Service;
import ru.dzhenenko.converter.Converter;
import ru.dzhenenko.dao.UserDao;
import ru.dzhenenko.entity.User;

@Service
public class AuthService {
    public UserDao userDao;
    public DigestService digestService;
    public Converter<User, UserDTO> userDtoConverter;

    public AuthService(UserDao userDao, DigestService digestService, Converter<User, UserDTO> userDtoConverter) {
        this.userDao = userDao;
        this.digestService = digestService;
        this.userDtoConverter = userDtoConverter;
    }

    public UserDTO getUserById(Long userId) {
        User user = userDao.findById(userId);
        if (user == null) {
            return null;
        }
        return userDtoConverter.convert(user);
    }

    // метод авторизации (сервис авторизации)
    public UserDTO auth(String email, String password) {
        String hash = digestService.hex(password);

        User user = userDao.findByEmailAndHash(email, hash);
        if (user == null) {
            return null;
        }

        return userDtoConverter.convert(user);
    }

    public UserDTO registration(String firstName, String lastName, String phone, String email, String password) {
        String hash = digestService.hex(password);

        User user = userDao.insert(firstName, lastName, phone, email, hash);
        if (user == null) {
            return null;
        }

        return userDtoConverter.convert(user);
    }
}
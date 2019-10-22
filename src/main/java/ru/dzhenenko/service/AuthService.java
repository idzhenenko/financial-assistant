package ru.dzhenenko.service;

import org.springframework.stereotype.Service;
import ru.dzhenenko.converter.Converter;
import ru.dzhenenko.converter.UserModelToUserDtoConverter;


import ru.dzhenenko.dao.UserDao;
import ru.dzhenenko.dao.UserModel;

@Service
public class AuthService {
    public UserDao userDao;
    public DigestService digestService;
    public Converter<UserModel, UserDTO> userDtoConverter;

    public AuthService(UserDao userDao, DigestService digestService, Converter<UserModel, UserDTO> userDtoConverter) {
        this.userDao = userDao;
        this.digestService = digestService;
        this.userDtoConverter = userDtoConverter;
    }

    public UserDTO getUserById(Long userId) {
        UserModel user = userDao.findById(userId);
        if (user == null) {
            return null;
        }
        return userDtoConverter.convert(user);
    }

    // метод авторизации (сервис авторизации)
    public UserDTO auth(String email, String password) {
        String hash = digestService.hex(password);

        UserModel userModel = userDao.findByEmailAndHash(email, hash);
        if (userModel == null) {
            return null;
        }

        return userDtoConverter.convert(userModel);
    }

    public UserDTO registration(String firstName, String lastName, String phone, String email, String password) {
        String hash = digestService.hex(password);

        UserModel userModel = userDao.insert(firstName, lastName, phone, email, hash);
        if (userModel == null) {
            return null;
        }

        return userDtoConverter.convert(userModel);
    }
}
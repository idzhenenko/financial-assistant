package ru.dzhenenko.service;

import ru.dzhenenko.converter.UserModelToUserDtoConverter;


import ru.dzhenenko.dao.UserDao;
import ru.dzhenenko.dao.UserModel;


public class AuthService {
    public UserDao userDao;
    public DigestService digestService;
    public UserModelToUserDtoConverter userDtoConverter;


    public AuthService() {
        this.userDao = new UserDao();
        this.digestService = new Md5DigestService();
        this.userDtoConverter = new UserModelToUserDtoConverter();
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

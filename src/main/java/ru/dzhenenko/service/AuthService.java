package ru.dzhenenko.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.dzhenenko.converter.UserModelToUserDtoConverter;
import ru.dzhenenko.dao.UserDao;
import ru.dzhenenko.dao.UserModel;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

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

    public UserDTO registration(String email, String password) {
        String hash = digestService.hex(password);

        UserModel userModel = userDao.insert(email, hash);
        if (userModel == null) {
            return null;
        }

        return userDtoConverter.convert(userModel);
    }

    public static void signUp(Connection conn, String first_name, String last1_name, String phone, String mail, String pass) throws SQLException {
        PreparedStatement st = conn.prepareStatement("INSERT INTO users (first_name, last_name, phone, email, password) VALUES (?, ?, ?, ?,?)");
        st.setString(1, first_name);
        st.setString(2, last1_name);
        st.setString(3, phone);
        st.setString(4, mail);
        st.setString(5, md5Hex(pass));
        st.executeUpdate();
        st.close();
    }
}

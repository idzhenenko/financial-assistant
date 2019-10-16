package ru.dzhenenko.dao;

import org.springframework.stereotype.Service;
import ru.dzhenenko.exeption.CustomExeption;

import javax.sql.DataSource;
import java.sql.*;

@Service
public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // поиск пользователя по почте и хэшу
    public UserModel findByEmailAndHash(String email, String hash) {
        UserModel userModel = null;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from users where email = ? and password = ?");
            ps.setString(1, email);
            ps.setString(2, hash);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                userModel = new UserModel();
                userModel.setId(rs.getLong("id"));
                userModel.setFirstName(rs.getString("first_name"));
                userModel.setLastName(rs.getString("last_name"));
                userModel.setPhone(rs.getString("phone"));
                userModel.setEmail(rs.getString("email"));
                userModel.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new CustomExeption(e);
        }
        return userModel;
    }

    public UserModel insert(String firstName, String lastName, String phone, String email, String hash) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users (first_name, last_name, phone, email, password) VALUES (?, ?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, phone);
            ps.setString(4, email);
            ps.setString(5, hash);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                UserModel userModel = new UserModel();
                userModel.setId(rs.getLong(1));
                userModel.setFirstName(firstName);
                userModel.setLastName(lastName);
                userModel.setPhone(phone);
                userModel.setEmail(email);
                userModel.setPassword(hash);

                return userModel;
            } else {
                throw new CustomExeption("Can't generate id!");
            }
        } catch (SQLException e) {
            throw new CustomExeption(e);
        }
    }
}
package ru.dzhenenko.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.dzhenenko.exeption.CustomExeption;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {
    private static DataSource dataSource;

    public AccountDao() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("postgres");

        dataSource = new HikariDataSource(config);
    }

    public static AccountModel findByUserId(Integer user_id) {
        AccountModel accountModel = null;

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM account WHERE user_id = ?");
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                AccountModel accountModel1 = new AccountModel();
                accountModel.setId(rs.getLong(1));
                return accountModel;
            } else {
                throw new CustomExeption("Че то не так!");
            }
        } catch (SQLException e) {
            throw new CustomExeption(e);
        }
    }
}

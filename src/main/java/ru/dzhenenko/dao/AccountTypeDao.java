package ru.dzhenenko.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.dzhenenko.exeption.CustomExeption;

import javax.sql.DataSource;
import java.sql.*;

public class AccountTypeDao {
    private static DataSource dataSource;
    private AccountTypeModel AccounTypeModel;

    public AccountTypeDao() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("postgres");

        dataSource = new HikariDataSource(config);
    }

    public AccountTypeModel addAccountType(String name) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement st = conn.prepareStatement("INSERT INTO category(name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, name);
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                AccountTypeModel accountTypeModel = new AccountTypeModel();
                accountTypeModel.setId(rs.getLong(1));
                accountTypeModel.setName(name);

                return AccounTypeModel;
            } else {
                throw new CustomExeption("Error adding!");
            }
        } catch (SQLException e) {
            throw new CustomExeption(e);
        }
    }

    public AccountTypeModel deleteAccountType(String name) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement st = conn.prepareStatement("delete from category where name = ?", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, name);
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                AccountTypeModel accountTypeModel = new AccountTypeModel();
                accountTypeModel.setId(rs.getLong(1));
                accountTypeModel.setName(name);

                return AccounTypeModel;
            } else {
                throw new CustomExeption("delete error!");
            }
        } catch (SQLException e) {
            throw new CustomExeption(e);
        }
    }

    public AccountTypeModel editAccountType(String name, int id) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement st = conn.prepareStatement("update category set name = ? where id = ?", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, name);
            st.setInt(2, id);
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                AccountTypeModel accountTypeModel = new AccountTypeModel();
                accountTypeModel.setId(rs.getLong(1));
                accountTypeModel.setName(name);
                accountTypeModel.setId(id);

                return AccounTypeModel;
            } else {
                throw new CustomExeption("Editing error!");
            }
        } catch (SQLException e) {
            throw new CustomExeption(e);
        }
    }
}
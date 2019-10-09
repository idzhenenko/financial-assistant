package ru.dzhenenko.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.dzhenenko.exeption.CustomExeption;

import javax.sql.DataSource;
import java.sql.*;

public class AccountTypeDao {
    private final DataSource dataSource;
    private AccountTypeModel AccounTypeModel;

    public AccountTypeDao(DataSource dataSource) {
        this.dataSource = dataSource;
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

                return accountTypeModel;
            } else {
                throw new CustomExeption("Error adding!");
            }
        } catch (SQLException e) {
            throw new CustomExeption(e);
        }
    }

    public AccountTypeModel deleteAccountType(int id) {
        AccountTypeModel accountTypeModel = new AccountTypeModel();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement st = conn.prepareStatement("delete from category where id = ?", Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, id);
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                accountTypeModel.getId(rs.getLong(1));
                accountTypeModel.getId(id);
            }
        } catch (SQLException e) {
            throw new CustomExeption(e);
        }
        return accountTypeModel;
    }
    public AccountTypeModel editAccountType(String name, int id) {
        AccountTypeModel accountTypeModel = new AccountTypeModel();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement st = conn.prepareStatement("update category set name = ? where id = ?", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, name);
            st.setInt(2, id);
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                accountTypeModel.setId(rs.getLong(1));
                accountTypeModel.setName(name);
                //accountTypeModel.setId(id);
            }
        } catch (SQLException e) {
            throw new CustomExeption(e);
        }
        return accountTypeModel;
    }
}
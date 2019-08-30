package ru.dzhenenko.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.dzhenenko.exeption.CustomExeption;
import ru.dzhenenko.service.UserDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private static DataSource dataSource;
    private AccountModel AccountModel;

    public AccountDao() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("postgres");

        dataSource = new HikariDataSource(config);
    }

    public AccountModel addAccount(String name, int balance) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement st = conn.prepareStatement("INSERT INTO account(name, balance, id_users) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, name);
            st.setInt(2, balance);
            //st.setLong(3, testId4);
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                AccountModel accountModel = new AccountModel();
                accountModel.setId(rs.getLong(1));
                accountModel.setName(name);
                accountModel.setBalance(balance);

                return AccountModel;
            } else {
                throw new CustomExeption("Error!");
            }
        } catch (SQLException e) {
            throw new CustomExeption(e);
        }
    }

    public AccountModel deleteAccount(String name) {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement st = conn.prepareStatement("delete from account where name = ?", Statement.RETURN_GENERATED_KEYS);
            st.setString(1, name);
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                AccountModel accountModel = new AccountModel();
                accountModel.setId(rs.getLong(1));
                accountModel.setName(name);

                return AccountModel;
            } else {
                throw new CustomExeption("Error!");
            }
        } catch (SQLException e) {
            throw new CustomExeption(e);
        }
    }
    //тут будет метод для просмотра счетов
    public List<AccountModel> viewAccountUser(long userId) {
        List<AccountModel> accountModels = new ArrayList<>();
        userId = 1;
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from account where id_users = ?");
            ps.setLong(1, userId);
            ResultSet query = ps.executeQuery();

            while (query.next()) {
                AccountModel accountModel = new AccountModel();
                accountModel.setId(query.getLong("id"));
                accountModel.setName(query.getString("name"));
                accountModel.setBalance(query.getLong("balance"));
                accountModel.setUserId(query.getLong("id_users"));
                accountModels.add(accountModel);
                //System.out.println("Id = " + query.getInt("id_users") + " " + query.getString("name") + " " + query.getInt("balance"));
                //return accountModels;
            }
        } catch (SQLException e) {
            throw new CustomExeption("Error!");
        }
        return accountModels;
    }
        public AccountModel findByUserId ( long userId){
            AccountModel accountModel = null;

            try (Connection conn = dataSource.getConnection()) {
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM account WHERE id_users = ?");
                ps.setInt(1, (int) userId);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    AccountModel accountModel1 = new AccountModel();
                    accountModel1.setId(rs.getLong(1));
                    return accountModel1;
                } else {
                    throw new CustomExeption("The account is not found!");
                }
            } catch (SQLException e) {
                throw new CustomExeption(e);
            }
        }
}

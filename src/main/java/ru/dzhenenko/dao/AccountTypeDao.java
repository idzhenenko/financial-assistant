package ru.dzhenenko.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class AccountTypeDao {
    private static DataSource dataSource;

    public AccountTypeDao() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("postgres");

        dataSource = new HikariDataSource(config);
    }
}

package ru.dzhenenko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(WebApplication.class, args);
    }
}

package ru.dzhenenko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import ru.dzhenenko.view.TerminalView;

import java.sql.SQLException;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(WebApplication.class, args);
    }
}
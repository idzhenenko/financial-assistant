package ru.dzhenenko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.dzhenenko.view.TerminalView;

import java.sql.SQLException;

@SpringBootApplication
public class WebApplication implements CommandLineRunner {

    @Autowired
    private TerminalView terminalView;

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(WebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        terminalView.start();
    }
}
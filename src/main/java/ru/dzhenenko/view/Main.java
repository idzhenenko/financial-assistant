package ru.dzhenenko.view;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import static ru.dzhenenko.service.AuthService.signUp;

public class Main {
    public static void main(String[] args) throws SQLException {
        AuthService authService = new AuthService();

        Scanner sc = new Scanner(System.in);
        System.out.println("*************************");
        System.out.println("======= MAIN MENU =======");
        System.out.println("1: Войти");
        System.out.println("2: Зарегистрироваться");
        System.out.println("3: Выход");
        int number = sc.nextInt();

        //System.out.println(userDTO);
        switch (number) {
            case 1:
                String email = request("Введите email:");
                String password = request("Введите password:");

                UserDTO userDTO = authService.auth(email, password);

                if (userDTO != null) {
                    System.out.println("HELLO" + " " + userDTO.getEmail() + "!");
                    //================= переменная ниже здесь. Пометка для себя
                    Integer testId = 1;
                    TerminalView.start(userDTO, testId);
                    break;
                } else {
                    System.out.println("Тебя нет в базе данных!");
                    break;
                }
            case 2:
                System.out.println("*************************");
                System.out.println("====== РЕГИСТРАЦИЯ ======");
                String first_name = request("Введите имя: ");
                String last1_name = request("Введите фамилию: ");
                String phone = request("Введите телефон: ");
                String mail = request("Введите Email: ");
                String pass = request("Введите password: ");
                System.out.println("You are successfully registered!");

                // Sign Up(Регистрация пользователя)
                final DataSource dataSource;
                HikariConfig config = new HikariConfig();
                config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
                config.setUsername("postgres");
                config.setPassword("postgres");

                dataSource = new HikariDataSource(config);
                try (Connection conn = dataSource.getConnection()) {

                    // метод для регистрации
                    signUp(conn, first_name, last1_name, phone, mail, pass);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                System.out.println("GOOD BYE!");
                //authService.registration();
        }
    }

    static String request(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(title);
        return scanner.next();
    }
}

package ru.dzhenenko.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.UserDTO;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.dzhenenko");

        AuthService authService = context.getBean(AuthService.class);

        Scanner sc = new Scanner(System.in);
        System.out.println("*************************");
        System.out.println("======= MAIN MENU =======");
        System.out.println("1: Войти");
        System.out.println("2: Зарегистрироваться");
        System.out.println("0: Выход");
        int number = sc.nextInt();

        switch (number) {
            case 1:
                String email = request("Введите email:");
                String password = request("Введите password:");

                UserDTO userDTO = authService.auth(email, password);
                if (userDTO != null) {
                    System.out.println("HELLO" + " " + userDTO.getEmail() + "!");
                    long userId1 = userDTO.getId();
                    TerminalView.start(userId1, userDTO);
                } else {
                    System.out.println("Тебя нет в базе данных!");
                }
                break;
            case 2:
                System.out.println("*************************");
                System.out.println("====== РЕГИСТРАЦИЯ ======");
                String first_name = request("Введите имя: ");
                String last1_name = request("Введите фамилию: ");
                String phone = request("Введите телефон: ");
                String mail = request("Введите Email: ");
                String pass = request("Введите password: ");
                System.out.println("You are successfully registered!");

                // метод для регистрации
                authService.registration(first_name, last1_name, phone, mail, pass);
                break;
            case 0:
                System.out.println("GOOD BYE!");
        }
    }

    static String request(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(title);
        return scanner.next();
    }
}

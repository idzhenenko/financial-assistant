package ru.dzhenenko.view;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.dzhenenko.service.UserDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Scanner;

import static ru.dzhenenko.service.AccountService.createAccount;
import static ru.dzhenenko.service.AccountService.deleteAccount;
import static ru.dzhenenko.service.AccountTypeService.*;

public class TerminalView {
    public static Integer testId3 = 1;

    public static void start(UserDTO userDto, long testId) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        boolean menuStatus = true;
        while (menuStatus) {
            System.out.println("MENU:");
            System.out.println("1: Показать счета");
            System.out.println("2: Показать транзакции");
            System.out.println("0: Выход");
            int input = scanner.nextInt();
            switch (input) {
                case 1:
                    final DataSource dataSource;
                    // метод для просмотра счетов
                    HikariConfig config = new HikariConfig();
                    config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
                    config.setUsername("postgres");
                    config.setPassword("postgres");

                    dataSource = new HikariDataSource(config);
                    try (Connection conn = dataSource.getConnection()) {
                        PreparedStatement preparedStatement = conn.prepareStatement("select * from users where id = ?");
                        preparedStatement.setLong(1, testId);
                        ResultSet rs = preparedStatement.executeQuery();
                        while (rs.next()) {
                        }
                        preparedStatement.close();

                        PreparedStatement ps = conn.prepareStatement("select * from users where id = ?");
                        ps.setLong(1, testId);
                        rs = ps.executeQuery();

                        if (rs.next()) {
                            testId = userDto.getId();
                            System.out.println("**********************");
                            System.out.println("YOUR ACCOUNTS: ");
                        } else {
                            System.out.println("Access denied!");

                        }

                        PreparedStatement ps1 = conn.prepareStatement("select * from account where id_users = ?");
                        ps1.setLong(1, testId);
                        rs = ps1.executeQuery();
                        while (rs.next())

                            System.out.println("Id = " + rs.getInt("id_users") + " " + rs.getString("name") + " " + rs.getInt("balance"));
                        ps1.close();
                        rs.close();


                        System.out.println("1: Создать счет");
                        System.out.println("2: Удалить счет");
                        System.out.println("3: Выход");
                        input = scanner.nextInt();
                        switch (input) {

                            case 1:
                                // Создать счет
                                if (input == 1) {
                                    System.out.println("Пожалуйста, введите детали счета:");
                                    System.out.println("Имя:");
                                    Scanner nameScan = new Scanner(System.in);
                                    String name = nameScan.next();

                                    System.out.println("Баланс:");
                                    Scanner balScan = new Scanner(System.in);
                                    int balance = balScan.nextInt();
                                    long testId4 = userDto.getId();

                                    System.out.println("Your account is successfully created!");

                                    Connection conn1 = null;
                                    try {
                                        conn1 = DriverManager.getConnection(
                                                "jdbc:postgresql://localhost:5432/postgres",
                                                "postgres",
                                                "postgres"
                                        );
                                        // метод создания счета
                                        createAccount(conn, name, balance, (int) testId4);
                                    } catch (SQLException e) {
                                        e.printStackTrace();
                                    }
                                }

                                break;
                            case 2:
                                System.out.println("**** УДАЛЕНИЕ СЧЕТА ****");
                                System.out.println("************************");
                                Scanner scanner2 = new Scanner(System.in);
                                System.out.println("Введите имя счета: ");
                                String name1 = scanner2.nextLine();

                                System.out.println("Your account is successfully deleted!");

                                Connection conn1 = null;
                                try {
                                    conn1 = DriverManager.getConnection(
                                            "jdbc:postgresql://localhost:5432/postgres",
                                            "postgres",
                                            "postgres"
                                    );
                                    // метод удаления счета
                                    deleteAccount(conn, name1);

                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case 3:
                                System.out.println("GOOD BYE!");
                                break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("**********************************");
                    System.out.println("====== КАТЕГОРИИ ТРАНЗАКЦИЙ ======");
                    System.out.println();
                    System.out.println("1: Создать транзакцию");
                    System.out.println("2: Удалить транзакцию");
                    System.out.println("3: Редактировать транзакцию");
                    System.out.println("0: Выход");
                    input = scanner.nextInt();

                    switch (input) {

                        case 1:
                            if (input == 1) {

                                System.out.println("*****************************");
                                System.out.println("==== СОЗДАНИЕ ТРАНЗАКЦИИ ====");

                                Scanner scanner3 = new Scanner(System.in);
                                System.out.println("Введите тип транзакции: ");
                                String name5 = scanner3.nextLine();

                                System.out.println("Your type account is successfully created!");

                                config = new HikariConfig();
                                config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
                                config.setUsername("postgres");
                                config.setPassword("postgres");

                                dataSource = new HikariDataSource(config);
                                try (Connection conn = dataSource.getConnection()) {
                                    // метод создания транзакции
                                    createAccountType(conn, name5, testId3);

                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                        case 2:
                            System.out.println("*****************************");
                            System.out.println("==== УДАЛЕНИЕ ТРАНЗАКЦИИ ====");

                            Scanner scanner3 = new Scanner(System.in);
                            System.out.println("Введите тип транзакции: ");
                            String name5 = scanner3.nextLine();

                            System.out.println("Your type account is successfully created!");

                            config = new HikariConfig();
                            config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
                            config.setUsername("postgres");
                            config.setPassword("postgres");

                            dataSource = new HikariDataSource(config);
                            try (Connection conn = dataSource.getConnection()) {
                                // метод удаления транзакции
                                deleteAccountType(conn, name5);

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            break;
                        case 3:
                            //тут будет редактирование транзакций
                            System.out.println("******************************");
                            System.out.println("= РЕДАКТИРОВАНИЕ ТРАНЗАКЦИИ ==");

                            config = new HikariConfig();
                            config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
                            config.setUsername("postgres");
                            config.setPassword("postgres");

                            String oldName = request("Введите имя для редактирования:");
                            int idBD = requestId("Ведите id:");
                            String newName = request("Введите новое имя:");

                            System.out.println("Транзакция успешно изменена c " + oldName + " на " + newName + " c id " + idBD + "!");

                            dataSource = new HikariDataSource(config);
                            try (Connection conn2 = dataSource.getConnection()) {
                                // метод редактирования транзакции
                                editingAccountType(conn2, newName, idBD);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            break;

                        case 0:
                            System.out.println("GOOD BYE!");
                            menuStatus = false;
                            break;
                        default:
                            System.out.println("Не понятный ввод!");
                    }
            }
        }
    }

    static String request(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(title);
        return scanner.next();
    }

    static Integer requestId(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(title);
        return scanner.nextInt();
    }
}

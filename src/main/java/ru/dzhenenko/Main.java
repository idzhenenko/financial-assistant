package ru.dzhenenko;

import java.sql.*;
import java.util.Scanner;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("*************************");
        System.out.println("======= Main menu =======");
        System.out.println("1. Sign up (Registration)");
        System.out.println("2. Sign in");
        System.out.println("Input the number: ");
        int number = sc.nextInt();

        if (number == 1) {
            System.out.println("*******Sign up*******");
            System.out.println("*********************");
            String first_name = requestString("Your first name: ");
            String last1_name = requestString("Your last name: ");
            String phone = requestString("Your phone: ");
            String mail = requestString("Your Email: ");
            String pass = requestString("Your password: ");
            System.out.println("You are successfully registered!");

            // Sign Up(Регистрация пользователя)
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "postgres"
                );

                // метод для регистрации
                signUp(conn,first_name,last1_name,phone,mail,pass);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            //-------------------------------------------------------------------------------------------------------------
        } else if (number == 2) {
            // Sign In(Авторизация пользователя)
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "postgres"
                );

                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Your email: ");
                String email1 = scanner1.nextLine();
                System.out.println("Your password: ");
                String password1 = scanner1.nextLine();
                String passwordHex1 = md5Hex(password1);

                System.out.println("======= Main menu =======");

                // метлод для авторизации
                signIn(conn, email1, passwordHex1);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            // Личный кабинет(показать все счета пользователя. Создать счет. Удалить счет)
            Scanner sc1 = new Scanner(System.in);
            System.out.println("*************************");
            System.out.println("======= Main menu =======");
            System.out.println("1. View accounts");
            System.out.println("2. Create account");
            System.out.println("3. Delete account");
            System.out.println("Input the number: ");
            int number1 = sc1.nextInt();

            if (number1 == 1) {
                //Scanner scanner = new Scanner(System.in);
                //System.out.println("Your email: ");
                //String email;
                //System.out.println("Your password: ");
                //String password;
                //String passwordHex = md5Hex(password);
                Integer testId = 1;// id юзера

                //Connection conn = null;
                try {
                    conn = DriverManager.getConnection(
                            "jdbc:postgresql://localhost:5432/postgres",
                            "postgres",
                            "postgres"
                    );
                    // метод выбора пользователя
                    personalAccount(conn, testId);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (number1 == 2) {
                //----------------------------------------------------------------------------------------------------------
                System.out.println("****Create account****");
                System.out.println("**********************");
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Account name: ");
                String name = scanner1.nextLine();
                System.out.println("Amount account: ");
                int balance = scanner1.nextInt();
                Integer testId = 1;

                System.out.println("Your account is successfully created!");

                //Connection conn = null;
                try {
                    conn = DriverManager.getConnection(
                            "jdbc:postgresql://localhost:5432/postgres",
                            "postgres",
                            "postgres"
                    );
                    // метод создания счета
                    createAccount(conn, name, balance, testId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else  if ( number1 == 3) {
                //------------------------------------------------------------------------------------------------------
                System.out.println("****Delete account****");
                System.out.println("**********************");
                Scanner scanner2 = new Scanner(System.in);
                System.out.println("Account name: ");
                String name1 = scanner2.nextLine();

                System.out.println("Your account is successfully deleted!");

                //Connection conn = null;
                try {
                    conn = DriverManager.getConnection(
                            "jdbc:postgresql://localhost:5432/postgres",
                            "postgres",
                            "postgres"
                    );
                    // метод удаления счета
                    deleteAccount(conn, name1);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    static String requestString(String title){
        Scanner scanner = new Scanner(System.in);
        System.out.println(title);
        return scanner.nextLine();
    }

    // метод для выбора пользователя
    public static void personalAccount(Connection conn, Integer testId) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement("select * from users where id = ?");
        preparedStatement.setInt(1, testId);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
        }
        preparedStatement.close();

        PreparedStatement ps = conn.prepareStatement("select * from users where id = ?");
        ps.setInt(1,testId);
        rs = ps.executeQuery();


        if (rs.next()) {
            testId = rs.getInt("id");
            System.out.println("**********************");
            System.out.println("Your accounts: ");
        } else {
            System.out.println("Access denied!");

        }
        PreparedStatement ps1 = conn.prepareStatement("select * from account where id_users = ?");
        ps1.setInt(1, testId);
        rs = ps1.executeQuery();
        while (rs.next())

            System.out.println("Id = " + rs.getInt("id_users")+ " " + rs.getString("name") + " " + rs.getInt("balance"));
        ps1.close();
        rs.close();
    }

    public static void createAccount(Connection conn, String name, int balance, Integer testId) throws SQLException {
        PreparedStatement st = conn.prepareStatement("INSERT INTO account(name, balance, id_users) VALUES (?, ?, ?)");
        st.setString(1, name);
        st.setInt(2, balance);
        st.setInt(3, testId);
        st.executeUpdate();
        st.close();
    }

    public static void deleteAccount(Connection conn, String name1) throws SQLException {
        PreparedStatement st1 = conn.prepareStatement("delete from account where name = ?");
        st1.setString(1, name1);
        st1.executeUpdate();
        st1.close();
    }

    public static void signIn(Connection conn, String email1, String passwordHex1) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("select * from users where email = ? and password = ?");
        ps.setString(1, email1);
        ps.setString(2, passwordHex1);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("  Hello, " + rs.getString("first_name")+ " " + rs.getString("last_name") + "!");
        } else {
            System.out.println("Access denied!");
        }
        ps.close();
        rs.close();
    }
    public static void signUp(Connection conn, String first_name, String last1_name, String phone, String mail, String pass) throws SQLException {
        PreparedStatement st = conn.prepareStatement("INSERT INTO users (first_name, last_name, phone, email, password) VALUES (?, ?, ?, ?,?)");
        st.setString(1, first_name);
        st.setString(2, last1_name);
        st.setString(3, phone);
        st.setString(4, mail);
        st.setString(5, md5Hex(pass));
        st.executeUpdate();
        st.close();
    }
}


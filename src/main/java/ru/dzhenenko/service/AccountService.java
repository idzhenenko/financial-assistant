package ru.dzhenenko.service;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AccountService {

    public static void createAccount(Connection conn, String name, int balance, Integer testId4) throws SQLException {
        PreparedStatement st = conn.prepareStatement("INSERT INTO account(name, balance, id_users) VALUES (?, ?, ?)");
        st.setString(1, name);
        st.setInt(2, balance);
        st.setInt(3, testId4);
        st.executeUpdate();
        st.close();
    }

    public static void deleteAccount(Connection conn, String name1) throws SQLException {
        PreparedStatement st1 = conn.prepareStatement("delete from account where name = ?");
        st1.setString(1, name1);
        st1.executeUpdate();
        st1.close();
    }

}

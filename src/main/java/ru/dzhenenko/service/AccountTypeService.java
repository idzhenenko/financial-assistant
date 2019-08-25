package ru.dzhenenko.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountTypeService {

    Integer idForDB = 1;

    public static void createAccountType(Connection conn, String name5, Integer testId3) throws SQLException {
        PreparedStatement st1 = conn.prepareStatement("INSERT INTO category(name) VALUES (?)");
        st1.setString(1, name5);
        //st1.setLong(2, testId3);
        //ResultSet rs = st1.executeQuery();
        //testId3 = rs.getInt("id");
        st1.executeUpdate();
        st1.close();
    }

    public static void deleteAccountType(Connection conn, String name1) throws SQLException {
        PreparedStatement st1 = conn.prepareStatement("delete from category where name = ?");
        st1.setString(1, name1);
        st1.executeUpdate();
        st1.close();
    }

    public static void editingAccountType(Connection conn2, String name, int id) throws SQLException {
        PreparedStatement statement = conn2.prepareStatement("update category set name = ? where id = ?");
        statement.setString(1, name);
        statement.setInt(2, id);
        statement.executeUpdate();
        // return statement.executeUpdate() = 1;
        statement.close();
    }
}

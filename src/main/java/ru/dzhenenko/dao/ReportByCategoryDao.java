package ru.dzhenenko.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.dzhenenko.exeption.CustomExeption;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportByCategoryDao {
    private final DataSource dataSource;
    public ReportByCategoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //метод для просмотра отчетов по категориям
    public List<ReportByCategoryModel> reportByCategory(long idUser, String startDay, String  endDay) {
        List<ReportByCategoryModel> reportByCategoryModels = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT t.amount, c.name\n" +
                    "                    FROM account AS a\n" +
                    "                            JOIN users u ON a.id_users = u.id\n" +
                    "                            LEFT JOIN transaction t ON a.id = t.source_account\n" +
                    "                            JOIN type_transaction tt ON t.id_type_transaction = tt.id\n" +
                    "                            JOIN id_tran_to_id_category ittic ON t.id = ittic.id_category\n" +
                    "                            JOIN category c ON ittic.id_transaction = c.id\n" +
                    "                    WHERE u.id = ? AND date_trunc('day', t.Create_date) >= ? AND date_trunc('day', t.Create_date) <= ?\n" +
                    "                    ");
            ps.setLong(1, idUser);
            ps.setDate(2, Date.valueOf(startDay));
            ps.setDate(3, Date.valueOf(endDay));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ReportByCategoryModel reportByCategoryModel = new ReportByCategoryModel();
                reportByCategoryModel.setName(rs.getString("name"));
                reportByCategoryModel.setAmount(rs.getLong("amount"));
                reportByCategoryModels.add(reportByCategoryModel);
            }
        } catch (SQLException e) {
            throw new CustomExeption(e.getMessage());
        }
        return reportByCategoryModels;
    }
}
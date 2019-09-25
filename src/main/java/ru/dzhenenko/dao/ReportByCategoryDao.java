package ru.dzhenenko.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import ru.dzhenenko.exeption.CustomExeption;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportByCategoryDao {
    private static DataSource dataSource;

    public ReportByCategoryDao() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setUsername("postgres");
        config.setPassword("postgres");

        dataSource = new HikariDataSource(config);
    }

    //метод для просмотра отчетов по категориям
    public List<ReportByCategoryModel> reportByCategory(long idUser, Timestamp startDay, Timestamp endDay) {
        List<ReportByCategoryModel> reportByCategoryModels = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT t.amount, c.name\n" +
                    "                    FROM account AS a\n" +
                    "                            JOIN users u ON a.id_users = u.id\n" +
                    "                            LEFT JOIN transaction t ON a.id = t.source_account\n" +
                    "                            JOIN type_transaction tt ON t.id_type_transaction = tt.id\n" +
                    "                            JOIN id_tran_to_id_category ittic ON t.id = ittic.id_category\n" +
                    "                            JOIN category c ON ittic.id_transaction = c.id\n" +
                    "                    WHERE u.id = ? AND t.create_date >= ? AND t.create_date <= ?\n" +
                    "                    ");
            ps.setLong(1, idUser);
            ps.setTimestamp(2, startDay);
            ps.setTimestamp(3, endDay);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ReportByCategoryModel reportByCategoryModel = new ReportByCategoryModel();
                reportByCategoryModel.setId(rs.getLong("id"));
                reportByCategoryModel.setSourceAccount(rs.getLong("source_account"));
                reportByCategoryModel.setTargetAccount(rs.getLong("target_account"));
                reportByCategoryModel.setCreateDate(rs.getTimestamp("create_date"));
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
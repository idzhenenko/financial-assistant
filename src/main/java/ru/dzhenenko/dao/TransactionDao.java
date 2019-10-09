package ru.dzhenenko.dao;

import ru.dzhenenko.exeption.CustomExeption;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionDao {
    private final DataSource dataSource;

    public TransactionDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public TransactionModel insertTransactions(long sourceAccount, long targetAccount, long balanceTransaction, String TypeTransaction,
                                               long idCategory, long idUser, String dateTransaction) {
        TransactionModel transactionModel = new TransactionModel();
        try (Connection conn = dataSource.getConnection()) {
            conn.setAutoCommit(false);
            PreparedStatement st1 = conn.prepareStatement("INSERT INTO transaction(source_account, target_account, create_date, id_type_transaction, amount) " +
                    "VALUES (?, ?, ?, ?, ?current_timestamp)", Statement.RETURN_GENERATED_KEYS);
            st1.setLong(1, sourceAccount);
            st1.setLong(2, targetAccount);
            st1.setLong(3, balanceTransaction);
            st1.setString(4, TypeTransaction);
            st1.setDate(5, Date.valueOf(LocalDate.now()));
            st1.executeUpdate();
            ResultSet rs1 = st1.getGeneratedKeys();

            if (rs1.next()) {
                transactionModel.setId(rs1.getLong(1));
                transactionModel.setAmount(balanceTransaction);
                transactionModel.setSourceAccount(sourceAccount);
                transactionModel.setTargetAccount(targetAccount);
                transactionModel.setTypeTransaction(TypeTransaction);
                transactionModel.setCreateDate(dateTransaction);
            }

            PreparedStatement st2 = conn.prepareStatement("INSERT INTO id_tran_to_id_category(id_transaction, id_category) VALUES(?,?)");
            st2.setLong(1, transactionModel.getId());
            st2.setLong(2, idCategory);
            st2.executeUpdate();

            PreparedStatement st3 = conn.prepareStatement("SELECT * FROM account WHERE id_users = ?");
            st3.setLong(1, idUser);
            st3.executeUpdate();

            PreparedStatement st4 = conn.prepareStatement("UPDATE account SET balance = ? WHERE id = ?");
            ResultSet rs2 = st3.executeQuery();

            while (rs2.next()) {
                AccountModel accountModel = new AccountModel();
                accountModel.setId(rs2.getLong("id"));
                accountModel.setName(rs2.getString("name"));
                accountModel.setBalance(rs2.getLong("balance"));
                accountModel.setUserId(rs2.getLong("id_users"));

                if (accountModel.getId() == sourceAccount) {
                    if (accountModel.getBalance() >= 0) {
                        st4.setLong(1,accountModel.getBalance());
                        st4.setLong(2,accountModel.getId());
                        st4.executeUpdate();
                    } else {
                        throw new CustomExeption("Your balance is less than the transaction amount");
                    }
                }
                if (accountModel.getId() == targetAccount) {
                    st4.setLong(1,accountModel.getBalance() + balanceTransaction);
                    st4.setLong(2,accountModel.getId());
                    st4.executeUpdate();
                }
                if (conn == null) {
                    conn.rollback();
                }
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionModel;
    }
}

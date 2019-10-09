package ru.dzhenenko.service;

import ru.dzhenenko.converter.Converter;
import ru.dzhenenko.dao.TransactionDao;
import ru.dzhenenko.dao.TransactionModel;

import java.sql.SQLException;

public class TransactionService {

    public TransactionDao transactionDao;
    public Converter<TransactionModel, TransactionDTO> transactionModelTransactionDTOConverter;

    public TransactionService(TransactionDao transactionDao, Converter<TransactionModel, TransactionDTO> transactionModelTransactionDTOConverter) {
        this.transactionDao = transactionDao;
        this.transactionModelTransactionDTOConverter = transactionModelTransactionDTOConverter;
    }

    public TransactionDTO insertTransaction(long amount, long sourceAccount, long targetAccount, String idTypeTransaction, long idCategory, long idUser, String dateTransaction) throws SQLException {

        TransactionModel transactionModel = transactionDao.insertTransactions(amount,sourceAccount,targetAccount,idTypeTransaction,idCategory,idUser,dateTransaction);
        if (transactionModel == null) {
            return null;
        }
        return transactionModelTransactionDTOConverter.convert(transactionModel);
    }
}

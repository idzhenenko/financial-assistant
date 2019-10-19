package ru.dzhenenko.service;

import org.springframework.stereotype.Service;
import ru.dzhenenko.converter.Converter;
import ru.dzhenenko.dao.TransactionDao;
import ru.dzhenenko.dao.TransactionModel;

import java.sql.SQLException;

@Service
public class TransactionService {
    public TransactionDao transactionDao;
    public Converter<TransactionModel, TransactionDTO> transactionModelTransactionDTOConverter;

    public TransactionService(TransactionDao transactionDao, Converter<TransactionModel, TransactionDTO> transactionModelTransactionDTOConverter) {
        this.transactionDao = transactionDao;
        this.transactionModelTransactionDTOConverter = transactionModelTransactionDTOConverter;
    }

    public TransactionDTO insertTransaction(long sourceAccount, long targetAccount, long amount,
                                            long idTypeTransaction, long idCategory, long idUser) throws SQLException {

        TransactionModel transactionModel = transactionDao.insertTransactions(sourceAccount, targetAccount, amount, idTypeTransaction, idCategory, idUser);
        if (transactionModel == null) {
            return null;
        }
        return transactionModelTransactionDTOConverter.convert(transactionModel);
    }
}

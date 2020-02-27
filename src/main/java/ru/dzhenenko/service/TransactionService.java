package ru.dzhenenko.service;

//@Service
public class TransactionService {
    /*public TransactionDao transactionDao;
    public Converter<Transaction, TransactionDTO> transactionModelTransactionDTOConverter;

    public TransactionService(TransactionDao transactionDao, Converter<Transaction, TransactionDTO> transactionModelTransactionDTOConverter) {
        this.transactionDao = transactionDao;
        this.transactionModelTransactionDTOConverter = transactionModelTransactionDTOConverter;
    }

    public TransactionDTO insertTransaction(long sourceAccount, long targetAccount, long amount,
                                            long idTypeTransaction, long idCategory, long idUser) throws SQLException {

        Transaction transaction = transactionDao.insertTransactions(sourceAccount, targetAccount, idTypeTransaction, idCategory, amount, idUser);
        if (transaction == null) {
            return null;
        }
        return transactionModelTransactionDTOConverter.convert(transaction);
    }*/
}

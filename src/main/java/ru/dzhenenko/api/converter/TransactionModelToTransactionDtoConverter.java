package ru.dzhenenko.api.converter;

import org.springframework.stereotype.Service;
import ru.dzhenenko.entity.Transaction;
import ru.dzhenenko.service.TransactionDTO;

@Service
public class TransactionModelToTransactionDtoConverter implements Converter<Transaction, TransactionDTO> {
    @Override
    public TransactionDTO convert(Transaction source) {
        TransactionDTO transaction = new TransactionDTO();
        transaction.setSourceAccount(source.getSourceAccount().getId());
        transaction.setTargetAccount(source.getTargetAccount().getId());
        transaction.setCreateDate(source.getCreateDate().toString());
        transaction.setTypeTransaction(source.getTypeTransaction().getId());
        transaction.setAmount(source.getAmount());
        return transaction;
    }
}


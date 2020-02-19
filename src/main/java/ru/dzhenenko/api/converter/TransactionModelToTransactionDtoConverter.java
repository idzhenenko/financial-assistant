package ru.dzhenenko.api.converter;

import org.springframework.stereotype.Service;
import ru.dzhenenko.entity.Transaction;
import ru.dzhenenko.service.TransactionDTO;

@Service
public class TransactionModelToTransactionDtoConverter implements Converter<Transaction, TransactionDTO> {
    @Override
    public TransactionDTO convert(Transaction source) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setSourceAccount(source.getSourceAccount().getId());
        transactionDTO.setTargetAccount(source.getTargetAccount().getId());
        transactionDTO.setCreateDate(source.getCreateDate().toString());
        transactionDTO.setTypeTransaction(source.getTypeTransaction().getId());
        transactionDTO.setAmount(source.getAmount());
        return transactionDTO;
    }
}


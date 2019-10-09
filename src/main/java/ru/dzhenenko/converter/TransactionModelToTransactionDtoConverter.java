package ru.dzhenenko.converter;

import ru.dzhenenko.dao.TransactionModel;
import ru.dzhenenko.service.TransactionDTO;

public class TransactionModelToTransactionDtoConverter implements Converter<TransactionModel, TransactionDTO> {
    @Override
    public TransactionDTO convert(TransactionModel source) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(source.getId());
        transactionDTO.setSourceAccount(source.getSourceAccount());
        transactionDTO.setTargetAccount(source.getTargetAccount());
        transactionDTO.setCreateDate(source.getCreateDate());
        transactionDTO.setTypeTransaction(source.getTypeTransaction());
        transactionDTO.setAmount(source.getAmount());
        return transactionDTO;
    }
}

package ru.dzhenenko.api.converter;

import org.springframework.stereotype.Component;
import ru.dzhenenko.api.json.TransactionResponse;
import ru.dzhenenko.entity.Transaction;

@Component
public class ServiceTransactionToResponseConverter implements Converter<Transaction, TransactionResponse>{
    @Override
    public TransactionResponse convert(Transaction transaction) {
        return new TransactionResponse(transaction.getId(), transaction.getSourceAccount(),
                transaction.getTargetAccount(), transaction.getCreateDate(),
                transaction.getTypeTransaction(), transaction.getAmount());
    }
}

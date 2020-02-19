package ru.dzhenenko.api.converter;

import org.springframework.stereotype.Component;
import ru.dzhenenko.api.json.AccountResponse;
import ru.dzhenenko.api.json.AddTypeTransactionResponse;
import ru.dzhenenko.entity.AccountType;

@Component
public class ServiceAccountTypeResponseConverter implements Converter<AccountType, AddTypeTransactionResponse>{
    @Override
    public AddTypeTransactionResponse convert(AccountType accountType) {
        return new AddTypeTransactionResponse(accountType.getId(),accountType.getName());
    }
}

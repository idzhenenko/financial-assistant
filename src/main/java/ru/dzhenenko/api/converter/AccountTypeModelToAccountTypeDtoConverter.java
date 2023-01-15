package ru.dzhenenko.api.converter;

import org.springframework.stereotype.Service;
import ru.dzhenenko.entity.AccountType;
import ru.dzhenenko.service.AccountTypeDTO;

@Service
public class AccountTypeModelToAccountTypeDtoConverter implements Converter<AccountType, AccountTypeDTO> {
    @Override
    public AccountTypeDTO convert(AccountType source) {
        AccountTypeDTO accountType = new AccountTypeDTO();
        accountType.setId(source.getId());
        accountType.setName(source.getName());
        return accountType;
    }
}
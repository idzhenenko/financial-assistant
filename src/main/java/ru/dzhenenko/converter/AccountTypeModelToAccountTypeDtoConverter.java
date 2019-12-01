package ru.dzhenenko.converter;

import org.springframework.stereotype.Service;
import ru.dzhenenko.entity.AccountType;
import ru.dzhenenko.service.AccountTypeDTO;

@Service
public class AccountTypeModelToAccountTypeDtoConverter implements Converter<AccountType, AccountTypeDTO> {
    @Override
    public AccountTypeDTO convert(AccountType source) {
        AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
        accountTypeDTO.setId(source.getId());
        accountTypeDTO.setName(source.getName());
        return accountTypeDTO;
    }
}
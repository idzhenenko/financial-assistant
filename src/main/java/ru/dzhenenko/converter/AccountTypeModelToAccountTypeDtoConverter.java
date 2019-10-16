package ru.dzhenenko.converter;

import org.springframework.stereotype.Service;
import ru.dzhenenko.dao.AccountTypeModel;
import ru.dzhenenko.service.AccountTypeDTO;

@Service
public class AccountTypeModelToAccountTypeDtoConverter implements Converter<AccountTypeModel, AccountTypeDTO> {
    @Override
    public AccountTypeDTO convert(AccountTypeModel source) {
        AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
        accountTypeDTO.setId(source.getId());
        accountTypeDTO.setName(source.getName());
        return accountTypeDTO;
    }
}
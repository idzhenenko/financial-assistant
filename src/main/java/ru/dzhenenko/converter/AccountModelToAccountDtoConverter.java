package ru.dzhenenko.converter;

import ru.dzhenenko.dao.AccountModel;
import ru.dzhenenko.service.AccountDTO;


public class AccountModelToAccountDtoConverter implements Converter<AccountModel, AccountDTO> {
    @Override
    public AccountDTO convert(AccountModel source) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(source.getId());
        accountDTO.setName(source.getName());
        accountDTO.setBalance(source.getBalance());
        accountDTO.setUserId(source.getUserId());
        return accountDTO;

    }
}
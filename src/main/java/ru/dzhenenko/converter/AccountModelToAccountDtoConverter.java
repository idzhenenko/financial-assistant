package ru.dzhenenko.converter;

import org.springframework.stereotype.Service;
import ru.dzhenenko.dao.AccountModel;
import ru.dzhenenko.service.AccountDTO;

@Service
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
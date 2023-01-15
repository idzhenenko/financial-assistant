package ru.dzhenenko.api.converter;

import org.springframework.stereotype.Service;
import ru.dzhenenko.entity.Account;
import ru.dzhenenko.service.AccountDTO;

@Service
public class AccountModelToAccountDtoConverter implements Converter<Account, AccountDTO> {
    @Override
    public AccountDTO convert(Account source) {
        AccountDTO account = new AccountDTO();
        account.setId(source.getId());
        account.setName(source.getName());
        account.setBalance(source.getBalance());
        account.setUserId(source.getUser().getId());
        return account;
    }
}
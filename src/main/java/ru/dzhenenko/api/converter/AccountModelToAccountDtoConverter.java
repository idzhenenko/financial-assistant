package ru.dzhenenko.api.converter;

import org.springframework.stereotype.Service;
import ru.dzhenenko.entity.Account;
import ru.dzhenenko.service.AccountDTO;

@Service
public class AccountModelToAccountDtoConverter implements Converter<Account, AccountDTO> {
    @Override
    public AccountDTO convert(Account source) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(source.getId());
        accountDTO.setName(source.getName());
        accountDTO.setBalance(source.getBalance());
        accountDTO.setUserId(source.getUser().getId());
        return accountDTO;
    }
}
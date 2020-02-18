package ru.dzhenenko.api.converter;

import org.springframework.stereotype.Component;
import ru.dzhenenko.api.json.AccountResponse;
import ru.dzhenenko.entity.Account;

@Component
public class ServiceAccountToResponseConverter implements Converter<Account, AccountResponse> {
    @Override
    public AccountResponse convert(Account account) {
        return new AccountResponse(account.getId(), account.getName(),
                account.getBalance(), account.getUser());
    }
}

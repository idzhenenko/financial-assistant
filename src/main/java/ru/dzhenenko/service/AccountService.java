package ru.dzhenenko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.api.converter.AccountModelToAccountDtoConverter;
import ru.dzhenenko.entity.Account;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.repository.ServiceAccountRepository;
import ru.dzhenenko.repository.ServiceUserRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountModelToAccountDtoConverter accountDtoConverter;
    private final ServiceAccountRepository accountRepository;
    private final ServiceUserRepository userRepository;

    public List<AccountDTO> viewAccount(long userId) {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        List<Account> account = accountRepository.findAllByUserId(userId);
        if (!account.isEmpty()) {
            for (Account item : account) {
                accountDTOS.add(accountDtoConverter.convert(item));
            }
        }
        return accountDTOS;
    }

    public AccountDTO createAccount(String name, long balance, long id) {
        Account account = new Account();
        User user = userRepository.getOne(id);
        account.setUser(user);
        account.setName(name);
        account.setBalance((int) balance);
        Account accountSave = accountRepository.save(account);
        return accountDtoConverter.convert(accountSave);
    }

    public AccountDTO removeAccount(long id) {
        Account account = accountRepository.getOne(id);
        accountRepository.delete(account);
        return accountDtoConverter.convert(account);
    }
}
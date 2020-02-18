package ru.dzhenenko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.api.converter.AccountModelToAccountDtoConverter;
import ru.dzhenenko.api.converter.Converter;
import ru.dzhenenko.dao.AccountDao;
import ru.dzhenenko.entity.Account;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.exeption.CustomExeption;
import ru.dzhenenko.repository.ServiceAccountRepository;
import ru.dzhenenko.repository.ServiceUserRepository;

import javax.persistence.EntityManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountModelToAccountDtoConverter accountDtoConverter;
    private final ServiceAccountRepository accountRepository;
    private final ServiceUserRepository userRepository;

    public List<AccountDTO> viewAccount(long userId) throws SQLException {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        List<Account> account = accountRepository.findAllByUser_Id(userId);

        if (!account.isEmpty()) {
            for (Account item : account) {
                accountDTOS.add(accountDtoConverter.convert(item));
            }
        }
        return accountDTOS;
    }

    public AccountDTO createAccount(String name, long balance, long id) throws SQLException {
        Account account = new Account();

        User user = userRepository.getOne(id);

        account.setUser(user);
        account.setName(name);
        account.setBalance((int) balance);

        Account accountSave = accountRepository.save(account);

        return accountDtoConverter.convert(accountSave);

    }

    public AccountDTO removeAccount(long id) throws SQLException {
        Account account = accountRepository.getOne(id);

        accountRepository.delete(account);

        return accountDtoConverter.convert(account);
    }
}
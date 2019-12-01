package ru.dzhenenko.service;

import org.springframework.stereotype.Service;
import ru.dzhenenko.converter.Converter;
import ru.dzhenenko.dao.AccountDao;
import ru.dzhenenko.entity.Account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    public AccountDao accountDao;
    public Converter<Account, AccountDTO> accountDtoConverter;

    public AccountService(AccountDao accountDao, Converter<Account, AccountDTO> accountDtoConverter) {
        this.accountDao = accountDao;
        this.accountDtoConverter = accountDtoConverter;
    }

    public AccountDTO createAccount(String name, int balance, long testId4) throws SQLException {

        Account account = accountDao.addAccount(name, balance, testId4);
        if (account == null) {
            return null;
        }

        return accountDtoConverter.convert(account);
    }

    public AccountDTO removeAccount(long id) throws SQLException {

        Account account = accountDao.deleteAccount(id);

        return accountDtoConverter.convert(account);
    }

    public List<AccountDTO> viewAccount(long userId) throws SQLException {
        List<AccountDTO> accountDTOS;
        List<Account> account = accountDao.viewAccountUser(userId);
        if (account == null) {
            return null;
        }
        List<AccountDTO> list = account.stream().map(item -> accountDtoConverter.convert(item)).collect(Collectors.toList());
        accountDTOS = list;

        return accountDTOS;
    }
}
package ru.dzhenenko.service;

import org.springframework.stereotype.Service;
import ru.dzhenenko.converter.Converter;
import ru.dzhenenko.dao.AccountDao;
import ru.dzhenenko.dao.AccountModel;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    public AccountDao accountDao;
    public Converter<AccountModel, AccountDTO> accountDtoConverter;

    public AccountService(AccountDao accountDao, Converter<AccountModel, AccountDTO> accountDtoConverter) {
        this.accountDao = accountDao;
        this.accountDtoConverter = accountDtoConverter;
    }

    public AccountDTO createAccount(String name, int balance, long testId4) throws SQLException {

        AccountModel accountModel = accountDao.addAccount(name, balance, testId4);
        if (accountModel == null) {
            return null;
        }

        return accountDtoConverter.convert(accountModel);
    }

    public AccountDTO removeAccount(long id) throws SQLException {

        AccountModel accountModel = accountDao.deleteAccount(id);
        if (accountModel == null) {
            return null;
        }

        return accountDtoConverter.convert(accountModel);
    }

    public List<AccountDTO> viewAccount(long userId) throws SQLException {
        List<AccountDTO> accountDTOS;
        List<AccountModel> accountModels = accountDao.viewAccountUser(userId);
        if (accountModels == null) {
            return null;
        }
        accountDTOS = accountModels.stream().map(item -> accountDtoConverter.convert(item)).collect(Collectors.toList());

        return accountDTOS;
    }
}
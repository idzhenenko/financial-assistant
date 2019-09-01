package ru.dzhenenko.service;

import ru.dzhenenko.converter.AccountModelToAccountDtoConverter;
import ru.dzhenenko.dao.AccountDao;
import ru.dzhenenko.dao.AccountModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountService {

    public AccountDao accountDao;
    public AccountModelToAccountDtoConverter accountDtoConverter;


    public AccountService() {
        this.accountDao = new AccountDao();
        this.accountDtoConverter = new AccountModelToAccountDtoConverter();
    }

    public AccountDTO createAccount(String name, int balance, long testId4) throws SQLException {

        AccountModel accountModel = accountDao.addAccount(name, balance, testId4);
        if (accountModel == null) {
            return null;
        }

        return accountDtoConverter.convert(accountModel);
    }


    public AccountDTO removeAccount(String name1) throws SQLException {

        AccountModel accountModel = accountDao.deleteAccount(name1);
        if(accountModel == null) {
            return null;
        }

        return accountDtoConverter.convert(accountModel);
    }
    public List<AccountDTO> viewAccount(long userId) throws SQLException {
        List<AccountDTO> accountDTOS = new ArrayList<>();
        List<AccountModel> accountModels = accountDao.viewAccountUser(userId);
        if (accountModels == null) {
            return null;
        }
        for (AccountModel item : accountModels) {
            accountDTOS.add(accountDtoConverter.convert(item));
        }
        return accountDTOS;
    }
}
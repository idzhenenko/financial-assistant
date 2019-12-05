package ru.dzhenenko.service;

import org.springframework.stereotype.Service;
import ru.dzhenenko.converter.Converter;
import ru.dzhenenko.dao.AccountTypeDao;
import ru.dzhenenko.entity.AccountType;

import java.sql.SQLException;

@Service
public class AccountTypeService {

    public AccountTypeDao accountTypeDao;
    public Converter<AccountType, AccountTypeDTO> accountTypeDtoConverter;

    public AccountTypeService(AccountTypeDao accountTypeDao, Converter<AccountType, AccountTypeDTO> accountTypeDtoConverter) {
        this.accountTypeDao = accountTypeDao;
        this.accountTypeDtoConverter = accountTypeDtoConverter;
    }

    public AccountTypeDTO createTypeAccount(String name) throws SQLException {

        AccountType accountType = accountTypeDao.addAccountType(name);
        if (accountType == null) {
            return null;
        }

        return accountTypeDtoConverter.convert(accountType);
    }

    public AccountTypeDTO removeAccountType(int id) throws SQLException {

        AccountType accountType = accountTypeDao.deleteAccountType(id);
        if (accountType == null) {
            return null;
        }
        return accountTypeDtoConverter.convert(accountType);
    }

    public AccountTypeDTO editingAccountType(String name, int id) throws SQLException {

        AccountType accountType = accountTypeDao.editAccountType(name, id);
        if (accountType == null) {
            return null;
        }
        return accountTypeDtoConverter.convert(accountType);
    }

}
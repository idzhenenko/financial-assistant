package ru.dzhenenko.service;

import ru.dzhenenko.converter.AccountTypeModelToAccountTypeDtoConverter;
import ru.dzhenenko.converter.Converter;
import ru.dzhenenko.dao.AccountTypeDao;
import ru.dzhenenko.dao.AccountTypeModel;

import java.sql.SQLException;

public class AccountTypeService {

    public AccountTypeDao accountTypeDao;
    public Converter<AccountTypeModel, AccountTypeDTO> accountTypeDtoConverter;

    public AccountTypeService(AccountTypeDao accountTypeDao, Converter<AccountTypeModel, AccountTypeDTO> accountTypeDtoConverter) {
        this.accountTypeDao = accountTypeDao;
        this.accountTypeDtoConverter = accountTypeDtoConverter;
    }

    public AccountTypeDTO createTypeAccount(String name) throws SQLException {

        AccountTypeModel accountTypeModel = accountTypeDao.addAccountType(name);
        if (accountTypeModel == null) {
            return null;
        }

        return accountTypeDtoConverter.convert(accountTypeModel);
    }

    public AccountTypeDTO removeAccountType(int id) throws SQLException {

        AccountTypeModel accountTypeModel = accountTypeDao.deleteAccountType(id);
        if (accountTypeModel == null) {
            return null;
        }
        return accountTypeDtoConverter.convert(accountTypeModel);
    }

    public AccountTypeDTO editingAccountType(String name, int id) throws SQLException {

        AccountTypeModel accountTypeModel = accountTypeDao.editAccountType(name, id);
        if (accountTypeModel == null) {
            return null;
        }
        return accountTypeDtoConverter.convert(accountTypeModel);
    }

}
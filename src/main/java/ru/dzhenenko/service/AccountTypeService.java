package ru.dzhenenko.service;

import ru.dzhenenko.converter.AccountTypeModelToAccountTypeDtoConverter;
import ru.dzhenenko.dao.AccountTypeDao;
import ru.dzhenenko.dao.AccountTypeModel;

import java.sql.SQLException;

public class AccountTypeService {

    public AccountTypeDao accountTypeDao;
    public AccountTypeModelToAccountTypeDtoConverter accountTypeDtoConverter;

    public AccountTypeService(AccountTypeDao accountTypeDao, AccountTypeModelToAccountTypeDtoConverter accountTypeDtoConverter) {
        this.accountTypeDao = accountTypeDao;
        this.accountTypeDtoConverter = accountTypeDtoConverter;
    }

    //=========================================================================
    //переделываем старый конструктор
    //public AccountTypeService() {
        //this.accountTypeDao = new AccountTypeDao();
        //this.accountTypeDtoConverter = new AccountTypeModelToAccountTypeDtoConverter();
    //}========================================================================

    public AccountTypeDTO createTypeAccount(String name) throws SQLException {

        AccountTypeModel accountTypeModel = accountTypeDao.addAccountType(name);
        if (accountTypeModel == null) {
            return null;
        }

        return accountTypeDtoConverter.convert(accountTypeModel);
    }

    public AccountTypeDTO removeAccountType(String name) throws SQLException {

        AccountTypeModel accountTypeModel = accountTypeDao.deleteAccountType(name);
        if (accountTypeModel == null) {
            return null;
        }
        return accountTypeDtoConverter.convert(accountTypeModel);
    }

    public AccountTypeDTO editingAccountType (String name, int id) throws SQLException {

        AccountTypeModel accountTypeModel = accountTypeDao.editAccountType(name, id);
        if (accountTypeModel == null) {
            return  null;
        }
        return accountTypeDtoConverter.convert(accountTypeModel);
    }

}


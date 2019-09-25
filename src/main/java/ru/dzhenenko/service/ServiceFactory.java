package ru.dzhenenko.service;

import ru.dzhenenko.converter.AccountModelToAccountDtoConverter;
import ru.dzhenenko.converter.AccountTypeModelToAccountTypeDtoConverter;
import ru.dzhenenko.converter.ReportByCategoryModelToReportByCategoryDtoConverter;
import ru.dzhenenko.converter.UserModelToUserDtoConverter;
import ru.dzhenenko.dao.AccountDao;
import ru.dzhenenko.dao.AccountTypeDao;
import ru.dzhenenko.dao.ReportByCategoryDao;
import ru.dzhenenko.dao.UserDao;

public class ServiceFactory {
    private static AuthService authService;
    private static AccountService accountService;
    private static AccountTypeService accountTypeService;
    private static ReportByCategoryService reportByCategoryService;

    public static AuthService getAuthService() {
        if (authService == null) {
            authService = new AuthService(
                    new UserDao(),
                    new Md5DigestService(),
                    new UserModelToUserDtoConverter()
            );
        }
        return authService;
    }

    public static AccountService getAccountService() {
        if (accountService == null) {
            accountService = new AccountService(
                    new AccountDao(),
                    new AccountModelToAccountDtoConverter()
            );
        }
        return accountService;
    }

    public static AccountTypeService getAccountTypeService() {
        if (accountTypeService == null) {
            accountTypeService = new AccountTypeService(
                    new AccountTypeDao(),
                    new AccountTypeModelToAccountTypeDtoConverter()
            );
        }
        return accountTypeService;
    }

    public static ReportByCategoryService getReportByCategoryService() {
        if (reportByCategoryService == null) {
            reportByCategoryService = new ReportByCategoryService(
                    new ReportByCategoryDao(),
                    new ReportByCategoryModelToReportByCategoryDtoConverter()
            );
        }
        return reportByCategoryService;
    }

}
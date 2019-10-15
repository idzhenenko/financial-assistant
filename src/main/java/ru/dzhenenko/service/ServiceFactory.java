package ru.dzhenenko.service;

import ru.dzhenenko.converter.AccountModelToAccountDtoConverter;
import ru.dzhenenko.converter.AccountTypeModelToAccountTypeDtoConverter;
import ru.dzhenenko.converter.ReportByCategoryModelToReportByCategoryDtoConverter;
import ru.dzhenenko.converter.UserModelToUserDtoConverter;


import static ru.dzhenenko.converter.ConverterFactory.*;
import static ru.dzhenenko.dao.DaoFactory.*;

public class ServiceFactory {
    private static AuthService authService;
    private static AccountService accountService;
    private static AccountTypeService accountTypeService;
    private static ReportByCategoryService reportByCategoryService;
    private static TransactionService transactionService;

    public static AuthService getAuthService() {
        if (authService == null) {
            authService = new AuthService(
                    getUserDao(),
                    getDigestService(),
                    getUserModelUserDTOConverter()
            );
        }
        return authService;
    }
    private static DigestService digestService;
    public static DigestService getDigestService() {
        if (digestService == null) {
            digestService = new Md5DigestService();
        }
        return digestService;
    }

    public static AccountService getAccountService() {
        if (accountService == null) {
            accountService = new AccountService(
                    getAccountDao(),
                    getAccountModelAccountDTOConverter()
            );
        }
        return accountService;
    }

    public static AccountTypeService getAccountTypeService() {
        if (accountTypeService == null) {
            accountTypeService = new AccountTypeService(
                    getAccountTypeDao(),
                    getAccountTypeModelAccountTypeDTOConverter()
            );
        }
        return accountTypeService;
    }

    public static ReportByCategoryService getReportByCategoryService() {
        if (reportByCategoryService == null) {
            reportByCategoryService = new ReportByCategoryService(
                    getReportByCategoryDao(),
                    getReportByCategoryModelReportByCategoryDTOConverter()
            );
        }
        return reportByCategoryService;
    }

    public static TransactionService getTransactionService() {
        if (transactionService == null) {
            transactionService = new TransactionService(
                    getTransactionDao(),
                    getTransactionModelTransactionDTOConverter()
            );
        }
        return transactionService;
    }
}
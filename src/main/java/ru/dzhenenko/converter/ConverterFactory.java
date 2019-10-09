package ru.dzhenenko.converter;

import ru.dzhenenko.dao.*;
import ru.dzhenenko.service.*;

public class ConverterFactory {
    public static Converter<UserModel, UserDTO> userModelUserDTOConverter;
    public static Converter<UserModel, UserDTO> getUserModelUserDTOConverter() {
        if (userModelUserDTOConverter == null) {
            userModelUserDTOConverter = new UserModelToUserDtoConverter();
        }
        return userModelUserDTOConverter;
    }

    public static Converter<ReportByCategoryModel, ReportByCategoryDTO> reportByCategoryModelReportByCategoryDTOConverter;
    public static Converter<ReportByCategoryModel, ReportByCategoryDTO> getReportByCategoryModelReportByCategoryDTOConverter() {
        if (reportByCategoryModelReportByCategoryDTOConverter == null) {
            reportByCategoryModelReportByCategoryDTOConverter = new ReportByCategoryModelToReportByCategoryDtoConverter();
        }
        return reportByCategoryModelReportByCategoryDTOConverter;
    }

    public static Converter<AccountTypeModel, AccountTypeDTO> accountTypeModelAccountTypeDTOConverter;
    public static Converter<AccountTypeModel, AccountTypeDTO> getAccountTypeModelAccountTypeDTOConverter() {
        if (accountTypeModelAccountTypeDTOConverter == null) {
            accountTypeModelAccountTypeDTOConverter = new AccountTypeModelToAccountTypeDtoConverter();
        }
        return accountTypeModelAccountTypeDTOConverter;
    }

    public static Converter<AccountModel, AccountDTO> accountModelAccountDTOConverter;
    public static Converter<AccountModel, AccountDTO> getAccountModelAccountDTOConverter() {
        if (accountModelAccountDTOConverter == null) {
            accountModelAccountDTOConverter = new AccountModelToAccountDtoConverter();
        }
        return accountModelAccountDTOConverter;
    }

    public static Converter<TransactionModel, TransactionDTO> transactionModelTransactionDTOConverter;
    public static Converter<TransactionModel, TransactionDTO> getTransactionModelTransactionDTOConverter() {
        if (transactionModelTransactionDTOConverter == null) {
            transactionModelTransactionDTOConverter = new TransactionModelToTransactionDtoConverter();
        }
        return transactionModelTransactionDTOConverter;
    }
}

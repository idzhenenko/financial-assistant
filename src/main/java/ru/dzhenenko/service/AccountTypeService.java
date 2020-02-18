package ru.dzhenenko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.api.converter.AccountModelToAccountDtoConverter;
import ru.dzhenenko.api.converter.AccountTypeModelToAccountTypeDtoConverter;
import ru.dzhenenko.api.converter.Converter;
import ru.dzhenenko.dao.AccountTypeDao;
import ru.dzhenenko.entity.AccountType;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.repository.ServiceAccountRepository;
import ru.dzhenenko.repository.ServiceAccountTypeRepository;
import ru.dzhenenko.repository.ServiceUserRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountTypeService {

    public AccountTypeDao accountTypeDao;
    public Converter<AccountType, AccountTypeDTO> accountTypeDtoConverter;
    private final AccountTypeModelToAccountTypeDtoConverter accountTypeModelToAccountTypeDtoConverter;
    private final ServiceAccountTypeRepository accountTypeRepository;
    private final ServiceUserRepository userRepository;

    public AccountTypeDTO createTypeAccount(String name, long idUser) throws SQLException {

        AccountType accountType = new AccountType();

        User user = userRepository.getOne(idUser);

        accountType.setUser(user);

        accountType.setName(name);

        AccountType accountTypeSave = accountTypeRepository.save(accountType);

        return accountTypeModelToAccountTypeDtoConverter.convert(accountTypeSave);
    }

    public AccountTypeDTO editingAccountType(String name, long id) throws SQLException {

        AccountType accountType = accountTypeRepository.findById(id);

        accountType.setName(name);

        accountTypeRepository.save(accountType);

        return accountTypeModelToAccountTypeDtoConverter.convert(accountType);
    }

    public AccountTypeDTO removeAccountType(long id) throws SQLException {

        AccountType accountType = accountTypeRepository.findById(id);

        accountTypeRepository.delete(accountType);

        return accountTypeModelToAccountTypeDtoConverter.convert(accountType);

    }

    // попробуем этот метод
    public List<AccountTypeDTO> viewTypeAccount(long id) {
        List<AccountTypeDTO> accountTypeDTOS = new ArrayList<>();
        List<AccountType> accountTypes = accountTypeRepository.findAllByUser_Id(id);

        if (!accountTypes.isEmpty()) {
            for (AccountType item : accountTypes) {
                accountTypeDTOS.add(accountTypeModelToAccountTypeDtoConverter.convert(item));
            }
        }
        return accountTypeDTOS;
    }

}
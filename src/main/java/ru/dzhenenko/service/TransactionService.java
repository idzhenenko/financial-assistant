package ru.dzhenenko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.api.converter.TransactionModelToTransactionDtoConverter;
import ru.dzhenenko.entity.Account;
import ru.dzhenenko.entity.AccountType;
import ru.dzhenenko.entity.Category;
import ru.dzhenenko.entity.Transaction;
import ru.dzhenenko.exeption.CustomExeption;
import ru.dzhenenko.repository.ServiceAccountRepository;
import ru.dzhenenko.repository.ServiceAccountTypeRepository;
import ru.dzhenenko.repository.ServiceCategoryRepository;
import ru.dzhenenko.repository.ServiceTransactionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final ServiceAccountRepository accountRepository;
    private final ServiceAccountTypeRepository accountTypeRepository;
    private final TransactionModelToTransactionDtoConverter transactionModelToTransactionDtoConverter;
    private final ServiceTransactionRepository transactionRepository;
    private final ServiceCategoryRepository serviceCategoryRepository;

    public TransactionDTO insertTransaction(Long sourceAccount, Long targetAccount, long idCategory,
                                            long idTypeTransaction, long amount, long idUser) {

        Account source = null;
        Account target = null;
        if (sourceAccount == 0 && targetAccount == 0)
            throw new CustomExeption("no accounts found");

        if (sourceAccount > 0) {
            source = accountRepository.getOne(sourceAccount);
            if (source == null) {
                throw new CustomExeption("Source accounts is null");
            }
            if (source.getUser().getId() != idUser) {
                throw new CustomExeption("Custom Error!!!(source)");
            }
        }

        if (targetAccount > 0) {
            target = accountRepository.getOne(targetAccount);
            if (target == null) {
                throw new CustomExeption("Target accounts is null");
            }
            if (target.getUser().getId() != idUser) {
                throw new CustomExeption("Custom Error!!!(target)");
            }
        }
        Transaction transaction = new Transaction();

        transaction.setSourceAccount(source);
        transaction.setTargetAccount(target);
        AccountType idTypeTrans = accountTypeRepository.getOne(idTypeTransaction);
        transaction.setTypeTransaction(idTypeTrans);
        transaction.setAmount(amount);
        transaction.setCreateDate(LocalDate.now());

        if (source.getId() == sourceAccount) {
            if (source.getBalance() > 0) {
                source.setBalance((int) (source.getBalance() - amount));
                accountRepository.save(source);
                source.getBalance();
                source.getId();
            } else {
                throw new CustomExeption("Your balance is less than the transaction amount");
            }
            if (target.getId() == targetAccount) {
                target.setBalance((int) (target.getBalance() + amount));
            }
        }

        Category category = serviceCategoryRepository.getOne(idCategory);
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        Transaction transactionSave = transactionRepository.save(transaction);
        return transactionModelToTransactionDtoConverter.convert(transactionSave);
    }
}

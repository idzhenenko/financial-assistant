package ru.dzhenenko.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.dzhenenko.JpaConfiguration;
import ru.dzhenenko.entity.Account;
import ru.dzhenenko.entity.AccountType;
import ru.dzhenenko.entity.Category;
import ru.dzhenenko.entity.Transaction;
import ru.dzhenenko.exeption.CustomExeption;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionDao {
    private final DataSource dataSource;

    public TransactionDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfiguration.class);
    EntityManager em = context.getBean(EntityManager.class);

    public Transaction insertTransactions(long sourceAccount, long targetAccount, long idTypeTransaction,
                                          long amount, long idCategory, long idUser) {
        EntityTransaction entityTransaction = em.getTransaction();
        entityTransaction.begin();
        Account source = em.find(Account.class, sourceAccount);
        Account target = em.find(Account.class, targetAccount);
        if (sourceAccount == 0 && targetAccount == 0)
            throw new CustomExeption("no accounts found");
        source = null;
        if (sourceAccount > 0) {
            source = em.find(Account.class, sourceAccount);
            if (source == null) {
                throw new CustomExeption("Source accounts is null");
            }
            if (source.getUser().getId() != idUser) {
                throw new CustomExeption("Custom Error!!!(source)");
            }
        }
        target = null;
        if (targetAccount > 0) {
            target = em.find(Account.class, targetAccount);
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
        AccountType idTypeTrans = em.find(AccountType.class, idTypeTransaction);
        transaction.setTypeTransaction(idTypeTrans);
        transaction.setAmount(amount);
        transaction.setCreateDate(LocalDate.now());

        if (source.getId() == sourceAccount) {
            if (source.getBalance() > 0) {
                source.setBalance((int) (source.getBalance() - amount));
                em.refresh(source);
                em.find(Account.class, sourceAccount);
                source.getBalance();
                source.getId();
            } else {
                throw new CustomExeption("Your balance is less than the transaction amount");
            }
            if (target.getId() == targetAccount) {
                target.setBalance((int) (target.getBalance() + amount));
            }
        }

        Category category = em.find(Category.class, idCategory);
        List<Category> categories = new ArrayList<>();
        categories.add(category);

        em.persist(transaction);
        entityTransaction.commit();
        return transaction;
    }
}
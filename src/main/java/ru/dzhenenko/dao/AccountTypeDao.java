package ru.dzhenenko.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.entity.AccountType;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Service
@AllArgsConstructor
public class AccountTypeDao {
    private final EntityManager em;

    public AccountType addAccountType(String name) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        AccountType accountType = new AccountType();
        accountType.setName(name);
        em.persist(accountType);
        transaction.commit();

        return accountType;
    }

    public AccountType deleteAccountType(int id) {
        AccountType accountType = em.find(AccountType.class, id);
        em.getTransaction().begin();
        em.remove(accountType);
        em.getTransaction().commit();

        return accountType;
    }

    public AccountType editAccountType(String name, int id) {
        AccountType accountType = em.find(AccountType.class, id);
        em.getTransaction().begin();
        accountType.setName(name);
        em.getTransaction().commit();

        return accountType;
    }
}
package ru.dzhenenko.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.entity.Account;
import ru.dzhenenko.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Service
@AllArgsConstructor
public class AccountDao {
    private final EntityManager em;

    public Account deleteAccount(long id) {
        Account account = em.find(Account.class, id);
        em.getTransaction().begin();
        em.remove(account);
        em.getTransaction().commit();

        return account;
    }

    public Account addAccount(String name, int balance, long testId) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        User user = em.find(User.class, testId);
        Account account = new Account();
        account.setName(name);
        account.setBalance(balance);
        account.setUser(user);
        em.persist(account);
        transaction.commit();

        return account;
    }

    public List<Account> viewAccountUser(long userId) {
        User user = em.find(User.class, userId);
        List<Account> accountList = em.createNamedQuery("Account.viewAccountUser", Account.class)
                .setParameter("user", user)
                .getResultList();
        return accountList;
    }

    public List<Account> findByUserId(long userId) {
        List<Account> resultList = em.createNamedQuery("Account.findByUserId", Account.class)
                .setParameter("id", userId)
                .getResultList();

        return resultList;
    }
}
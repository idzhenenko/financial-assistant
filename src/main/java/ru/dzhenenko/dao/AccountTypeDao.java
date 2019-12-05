package ru.dzhenenko.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.dzhenenko.entity.AccountType;
import ru.dzhenenko.JpaConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.sql.DataSource;

@Service
public class AccountTypeDao {
    private final DataSource dataSource;

    public AccountTypeDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfiguration.class);
    EntityManager em = context.getBean(EntityManager.class);

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
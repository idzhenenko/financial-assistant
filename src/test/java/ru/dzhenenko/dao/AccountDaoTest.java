package ru.dzhenenko.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AccountDaoTest {

    ApplicationContext context = new AnnotationConfigApplicationContext("ru.dzhenenko");
    AccountDao subj;

    @Before
    public void setUp() throws Exception {
        System.setProperty("jdbcUrl", "jdbc:h2:mem:test_mem" + UUID.randomUUID().toString());
        System.setProperty("jdbcUser", "sa");
        System.setProperty("jdbcPassword", "");
        System.setProperty("liquibaseFile", "liquibase_account_dao_test.xml");

        subj = context.getBean(AccountDao.class);
    }
    @Test
    public void addAccount() {
        AccountModel account = subj.addAccount("Bank", 12300000,1);

        assertEquals("Bank",account.getName());
        assertEquals(12300000,account.getBalance());
    }

    @Test
    public void viewAccountUser() {
        List<AccountModel> accountModels = subj.viewAccountUser(1);

        assertNotNull(accountModels);
    }
    @Test
    public void findByUserId() {
        AccountModel account = subj.findByUserId(1);

        assertNotNull(account);
    }

    @Test
    public void deleteAccount() {
        AccountModel accountModel = subj.deleteAccount(1);
        assertNotEquals(1, accountModel.getId());

        assertNotNull(accountModel);
    }
}
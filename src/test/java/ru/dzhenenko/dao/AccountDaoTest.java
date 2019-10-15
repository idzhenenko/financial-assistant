package ru.dzhenenko.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AccountDaoTest {

    AccountDao subj;

    @Before
    public void setUp() throws Exception {
        System.setProperty("jdbcUrl", "jdbc:h2:mem:test_mem" + UUID.randomUUID().toString());
        System.setProperty("jdbcUser", "sa");
        System.setProperty("jdbcPassword", "");
        System.setProperty("liquibaseFile", "liquibase_account_dao_test.xml");

        subj = DaoFactory.getAccountDao();
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

        //assertEquals(1,account.getId());
        assertNotNull(account);
    }

    @Test
    public void deleteAccount() {
        AccountModel accountModel = subj.deleteAccount(1);
        assertNotEquals(1, accountModel.getId());

        assertNotNull(accountModel);
    }
}
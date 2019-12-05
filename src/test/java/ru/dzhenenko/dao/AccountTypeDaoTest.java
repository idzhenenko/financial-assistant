package ru.dzhenenko.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dzhenenko.entity.AccountType;

import java.util.UUID;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class AccountTypeDaoTest {
    AccountTypeDao subj;

    @Before
    public void setUp() throws Exception {
        System.setProperty("jdbcUrl", "jdbc:h2:mem:test_mem" + UUID.randomUUID().toString());
        System.setProperty("jdbcUser", "sa");
        System.setProperty("jdbcPassword", "");
        System.setProperty("liquibaseFile", "liquibase_account_type_dao_test.xml");

        ApplicationContext context = new AnnotationConfigApplicationContext("ru.dzhenenko");
        subj = context.getBean(AccountTypeDao.class);
    }

    @Test
    public void addAccountType() {
        AccountType trips = subj.addAccountType("Products");

        assertEquals("Products", trips.getName());
        assertNotEquals("Trips", trips.getName());
        assertNotNull(trips);
    }
    @Test
    public void deleteAccountType() {
        AccountType trips = subj.deleteAccountType(3);

        assertNotEquals(3, trips.getName());
        //assertNotEquals(1,trips.getId());
        assertNotNull(trips);
    }

    @Test
    public void editAccountType() {
        AccountType trips = subj.editAccountType("Products",1);

        assertNotNull(trips);
        System.out.println(trips.toString());

    }
}
package ru.dzhenenko.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AccountTypeDaoTest {

    AccountTypeDao subj;

    @Before
    public void setUp() throws Exception {
        System.setProperty("jdbcUrl", "jdbc:h2:mem:test_mem");
        System.setProperty("jdbcUser", "sa");
        System.setProperty("jdbcPassword", "");
        System.setProperty("liquibaseFile", "liquibase_account_type_dao_test.xml");

        subj = DaoFactory.getAccountTypeDao();
    }

    @After
    public void after() {
        DaoFactory.dataSource = null;
    }

    @Test
    public void addAccountType() {
        AccountTypeModel trips = subj.addAccountType("Products");

        assertEquals("Products", trips.getName());
        assertNotEquals("Trips", trips.getName());
        assertNotNull(trips);
    }
    @Test
    public void deleteAccountType() {
        AccountTypeModel trips = subj.deleteAccountType(1);

        assertNotEquals(1, trips.getName());
        assertNotEquals(1,trips.getId());
        assertNotNull(trips);

    }
    @Test
    public void editAccountType() {
        AccountTypeModel trips = subj.editAccountType("Products",1);

        //assertEquals("Products", trips.getName());
        //assertEquals(1,trips.getId());
        assertNotNull(trips);
    }
}
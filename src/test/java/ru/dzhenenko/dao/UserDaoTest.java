package ru.dzhenenko.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

import static org.junit.Assert.*;
public class UserDaoTest {

    ApplicationContext context = new AnnotationConfigApplicationContext("ru.dzhenenko");
    UserDao subj;

    @Before
    public void setUp() throws Exception {
        System.setProperty("jdbcUrl", "jdbc:h2:mem:test_mem" + UUID.randomUUID().toString());
        System.setProperty("jdbcUser", "sa");
        System.setProperty("jdbcPassword", "");
        System.setProperty("liquibaseFile", "liquibase_user_dao_test.xml");

        subj = context.getBean(UserDao.class);
    }

    @Test
    public void findByEmailAndHash() {
        UserModel user = subj.findByEmailAndHash("i.dzhenenko@gmail.com", "8046f82d3755e422ae19b026791a5e72");

        assertEquals(1L, user.getId());
        assertEquals("i.dzhenenko@gmail.com", user.getEmail());
        assertEquals("8046f82d3755e422ae19b026791a5e72", user.getPassword());
    }

    @Test
    public void findByEmailAndHash_notFound() {
        UserModel user = subj.findByEmailAndHash("i.dzhenenko@gmail.com", "8046f82d3755e422ae19b026791a5e76");

        assertNull(user);
    }

    @Test
    public void insert() {
        UserModel insert = subj.insert("Olga", "Dzhenenko", "+8800", "olgaginger20.05@gmail.com", "91fb031037175b9fc3179b02f4c07e24");
        assertEquals("Olga",insert.getFirstName());
        assertEquals("Dzhenenko",insert.getLastName());
        assertEquals("+8800", insert.getPhone());
        assertEquals("olgaginger20.05@gmail.com", insert.getEmail());
        assertEquals("91fb031037175b9fc3179b02f4c07e24", insert.getPassword());
    }

}
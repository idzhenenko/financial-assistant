package ru.dzhenenko.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.secuity.UserRole;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RunWith(SpringRunner.class)
class ServiceUserRepositoryTest {

    @Autowired ServiceUserRepository subj;
    @Autowired EntityManager em;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void findByEmailAndPassword() {
    }

    @Test
    public void findByEmail() {
        User user = subj.findByEmail("i.dzhenenko@gmail.com");

        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("i.dzhenenko@gmail.com", user.getEmail());
        assertEquals("$2y$12$1GkGr4hhAEX0exlONRfnYu5St31AuKf5xjIEXq7GWiO91iSLzKH2e", user.getPassword());
    }

    @Test
    public void findByFilter() {


        /*ServiceUserFilter filter = new ServiceUserFilter().setEmailLike("i.dzhenenko%")
                                                          .setUserRole(UserRole.USER);

        List<User> users = subj.findByFilter(filter);

        assertNotNull(users);
        assertEquals(1, users.size());*/
    }
}
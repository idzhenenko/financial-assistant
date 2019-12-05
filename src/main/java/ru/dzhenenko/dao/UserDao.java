package ru.dzhenenko.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import ru.dzhenenko.JpaConfiguration;
import ru.dzhenenko.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.sql.DataSource;
import java.util.List;

@Service
public class UserDao {
    private final DataSource dataSource;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfiguration.class);
    EntityManager em = context.getBean(EntityManager.class);

    // поиск пользователя по почте и хэшу
    public User findByEmailAndHash(String email, String hash) {
        List<User> user = em.createNamedQuery("Users.getByEmailAndPassword", User.class)
                .setParameter("email", email)
                .setParameter("password", hash)
                .getResultList();
        if (user.isEmpty()) {
            return null;
        } else {
            return user.get(0);
        }
    }

    public User insert(String firstName, String lastName, String phone, String email, String hash) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(hash);
        em.persist(user);
        transaction.commit();
        return user;
    }

    public User findById(Long userId) {
        User user = em.createNamedQuery("Users.getById", User.class)
                .setParameter("id", userId)
                .getSingleResult();
        return user;
    }
}

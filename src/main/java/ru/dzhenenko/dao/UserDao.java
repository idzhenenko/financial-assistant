package ru.dzhenenko.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDao {
    private final EntityManager em;

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

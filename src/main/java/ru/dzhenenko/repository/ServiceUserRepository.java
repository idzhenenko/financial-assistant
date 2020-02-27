package ru.dzhenenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dzhenenko.entity.User;

public interface ServiceUserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);

}
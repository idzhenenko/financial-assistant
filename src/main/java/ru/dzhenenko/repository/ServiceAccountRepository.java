package ru.dzhenenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.dzhenenko.entity.Account;

import java.util.List;

public interface ServiceAccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAllByUser_Id(long userId);
}

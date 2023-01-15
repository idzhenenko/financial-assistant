package ru.dzhenenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dzhenenko.entity.AccountType;

import java.util.List;

public interface ServiceAccountTypeRepository extends JpaRepository<AccountType, Long> {
    AccountType findById(long id);

    List<AccountType> findAllByUserId(long id);
}

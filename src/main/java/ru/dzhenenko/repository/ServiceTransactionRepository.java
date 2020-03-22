package ru.dzhenenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dzhenenko.entity.Transaction;

public interface ServiceTransactionRepository extends JpaRepository<Transaction, Long> {
}

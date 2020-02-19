package ru.dzhenenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dzhenenko.entity.Account;
import ru.dzhenenko.entity.ReportByCategory;
import ru.dzhenenko.entity.User;

import java.util.List;

public interface ServiceReportByCategoryRepository extends JpaRepository<User, Long> {

}
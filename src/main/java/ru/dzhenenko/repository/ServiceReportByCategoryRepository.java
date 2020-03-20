package ru.dzhenenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.dzhenenko.entity.Account;
import ru.dzhenenko.entity.ReportByCategory;
import ru.dzhenenko.entity.Transaction;
import ru.dzhenenko.entity.User;

import javax.persistence.NamedNativeQueries;
import java.lang.annotation.Native;
import java.util.List;

public interface ServiceReportByCategoryRepository extends JpaRepository<ReportByCategory, Long>, ServiceReportByCategoryRepositoryCustom {

}
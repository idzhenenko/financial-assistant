package ru.dzhenenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dzhenenko.entity.ReportByCategory;

public interface ServiceReportByCategoryRepository extends JpaRepository<ReportByCategory, Long>, ServiceReportByCategoryRepositoryCustom {

}

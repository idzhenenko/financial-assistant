package ru.dzhenenko.repository;

import ru.dzhenenko.entity.ReportByCategory;
import ru.dzhenenko.entity.Transaction;

import java.util.List;

public interface ServiceReportByCategoryRepositoryCustom {

    List<ReportByCategory> findByUserIdAndStartDateAndEndDate(Long userId, String startDate, String endDate);
}

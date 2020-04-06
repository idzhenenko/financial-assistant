package ru.dzhenenko.repository;

import lombok.RequiredArgsConstructor;
import ru.dzhenenko.entity.ReportByCategory;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
public class ServiceReportByCategoryRepositoryImpl implements ServiceReportByCategoryRepositoryCustom{
    private final EntityManager em;

   @Override
   public List<ReportByCategory> findByUserIdAndStartDateAndEndDate(Long userId, String startDate, String endDate) {
       Timestamp startTimestamp = Timestamp.valueOf(startDate + " 00:00:00.000000");
       Timestamp endTimestamp = Timestamp.valueOf(endDate + " 00:00:00.000000");

       Query nativeQuery = em.createNativeQuery(
               "SELECT cat.name, sum(t.amount) as amount " +
                       "FROM category AS cat " +
                       "INNER JOIN users u on cat.id_users = u.id " +
                       "INNER JOIN id_tran_to_id_category ctt on cat.id = ctt.id_category " +
                       "INNER JOIN transaction t on ctt.id_transaction = t.id " +
                       "WHERE u.id = ? AND t.create_date between ? AND ? " +
                       "GROUP BY cat.name;", ReportByCategory.class);
       nativeQuery.setParameter(1, userId);
       nativeQuery.setParameter(2, startTimestamp);
       nativeQuery.setParameter(3, endTimestamp);

       return (List<ReportByCategory>) nativeQuery.getResultList();

    }
}

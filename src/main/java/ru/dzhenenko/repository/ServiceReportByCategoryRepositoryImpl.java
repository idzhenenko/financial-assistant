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

   /* @SuppressWarnings("unchecked")
    @Override
    public List<Transaction> findByUserId(Long userId) {
        Query query = em.createNamedQuery(
                "SELECT t.amount, c.name FROM account AS a JOIN users u ON a.id_users = u.id " +
                        "LEFT JOIN transaction t ON a.id = t.source_account " +
                        "JOIN type_transaction tt ON t.id_type_transaction = tt.id " +
                        "JOIN id_tran_to_id_category ittic ON t.id = ittic.id_category " +
                        "JOIN category c ON ittic.id_transaction = c.id WHERE u.id = ? " +
                        "AND date_trunc('day', t.Create_date) >= ? AND date_trunc('day', t.Create_date) <= ?", Transaction.class);
        query.setParameter(1, userId);
        return (List<Transaction>) query.getResultList();*/
   @SuppressWarnings("unchecked")
   @Override
   public List<ReportByCategory> findByUserIdAndStartDateAndEndDate(Long userId, String startDate, String endDate) {
       Timestamp startTimestamp = Timestamp.valueOf(startDate + " 00:00:00.000000");
       Timestamp endTimestamp = Timestamp.valueOf(endDate + " 00:00:00.000000");

       Query nativeQuery = em.createNativeQuery(
               "SELECT cat.name, sum(t.balance_tr) as balance " +
                       "FROM category AS cat " +
                       //"INNER JOIN users u on cat.user_id = u.id " +
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

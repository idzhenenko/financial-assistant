package ru.dzhenenko.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.entity.ReportByCategory;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportByCategoryDao {
    private final EntityManager em;


    public List<ReportByCategory> reportByCategory(long idUser, String startDay, String endDay) {
        List resultList = em.createNativeQuery("SELECT tt.name,c.name,t.amount, t.create_date FROM account AS a JOIN users u ON a.id_users = u.id LEFT" +
                "                JOIN transaction t ON a.id = t.source_account" +
                "                JOIN type_transaction tt ON t.id_type_transaction = tt.id" +
                "                JOIN id_tran_to_id_category ittic ON t.id_type_transaction = ittic.id_transaction" +
                "                JOIN category c ON ittic.id_category = c.id" +
                "                WHERE u.id = ? AND date_trunc('day', t.Create_date) >= ? AND date_trunc('day', t.Create_date) <= ?", ReportByCategory.class)
                .setParameter(1, idUser)
                .setParameter(2, Date.valueOf(startDay))
                .setParameter(3, Date.valueOf(endDay))
                .getResultList();

        return resultList;
    }
}
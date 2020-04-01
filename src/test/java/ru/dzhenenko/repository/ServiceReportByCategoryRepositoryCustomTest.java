package ru.dzhenenko.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.dzhenenko.entity.Account;
import ru.dzhenenko.entity.Category;
import ru.dzhenenko.entity.ReportByCategory;
import ru.dzhenenko.entity.Transaction;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@RunWith(SpringRunner.class)
class ServiceReportByCategoryRepositoryCustomTest {

    @Autowired ServiceReportByCategoryRepository subj;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByUserIdAndStartDateAndEndDate() {
        ReportByCategory category = new ReportByCategory();
        category.setName("Самолет");
        category.setAmount(120000);

        List<ReportByCategory> categoryList = new ArrayList<>();
        categoryList.add(category);

        List<ReportByCategory> report = subj.findByUserIdAndStartDateAndEndDate(1L,
                "2020-10-10", "2020-10-20");

        assertNotNull(report);
        System.out.println("report" + report);
        assertEquals(categoryList,report);
    }
}
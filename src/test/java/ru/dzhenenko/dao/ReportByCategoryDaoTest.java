package ru.dzhenenko.dao;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dzhenenko.entity.ReportByCategory;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class ReportByCategoryDaoTest {

    ApplicationContext context = new AnnotationConfigApplicationContext("ru.dzhenenko");
    ReportByCategoryDao subj;

    @Before
    public void setUp() throws Exception {
        System.setProperty("jdbcUrl", "jdbc:h2:mem:test_mem" + UUID.randomUUID().toString());
        System.setProperty("jdbcUser", "sa");
        System.setProperty("jdbcPassword", "");
        System.setProperty("liquibaseFile", "liquibase_report_by_category_dao_test.xml");

        subj = context.getBean(ReportByCategoryDao.class);
    }

    @Test
    public void reportByCategory() {
        List<ReportByCategory> reportByCategoryModels = subj.reportByCategory(1, "2019-07-07", "2019-09-09");

        assertNotNull(reportByCategoryModels);

    }
}
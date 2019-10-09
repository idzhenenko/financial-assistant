package ru.dzhenenko.dao;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class ReportByCategoryDaoTest {

    ReportByCategoryDao subj;

    @Before
    public void setUp() throws Exception {
        System.setProperty("jdbcUrl", "jdbc:h2:mem:test_mem" + UUID.randomUUID().toString());
        System.setProperty("jdbcUser", "sa");
        System.setProperty("jdbcPassword", "");
        System.setProperty("liquibaseFile", "liquibase_report_by_category_dao_test.xml");

        subj = DaoFactory.getReportByCategoryDao();
    }

    @Test
    public void reportByCategory() {
        List<ReportByCategoryModel> reportByCategoryModels = subj.reportByCategory(1, "2019-07-07", "2019-09-09");

        assertNotNull(reportByCategoryModels);
    }
}
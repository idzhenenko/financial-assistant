package ru.dzhenenko.service;

import ru.dzhenenko.converter.ReportByCategoryModelToReportByCategoryDtoConverter;
import ru.dzhenenko.dao.ReportByCategoryDao;
import ru.dzhenenko.dao.ReportByCategoryModel;
import ru.dzhenenko.exeption.CustomExeption;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ReportByCategoryService {

    public ReportByCategoryDao reportByCategoryDao;
    public ReportByCategoryModelToReportByCategoryDtoConverter reportByCategoryModelToReportByCategoryDtoConverter;

    public ReportByCategoryService(ReportByCategoryDao reportByCategoryDao, ReportByCategoryModelToReportByCategoryDtoConverter reportByCategoryModelToReportByCategoryDtoConverter) {
        this.reportByCategoryDao = reportByCategoryDao;
        this.reportByCategoryModelToReportByCategoryDtoConverter = reportByCategoryModelToReportByCategoryDtoConverter;
    }

    public List<ReportByCategoryDTO> viewReportCategory(long idUser,Timestamp startDay, Timestamp endDay) throws SQLException {
        List<ReportByCategoryDTO> reportByCategoryDTOS = new ArrayList<>();
        List<ReportByCategoryModel> reportByCategoryModels = reportByCategoryDao.reportByCategory(idUser,startDay,endDay);
        if (reportByCategoryModels == null) {
            return null;
        }
        for (ReportByCategoryModel item : reportByCategoryModels) {
            reportByCategoryDTOS.add(reportByCategoryModelToReportByCategoryDtoConverter.convert(item));
        }
        return reportByCategoryDTOS;
    }
}

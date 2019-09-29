package ru.dzhenenko.service;

import ru.dzhenenko.converter.ReportByCategoryModelToReportByCategoryDtoConverter;
import ru.dzhenenko.dao.ReportByCategoryDao;
import ru.dzhenenko.dao.ReportByCategoryModel;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public class ReportByCategoryService {

    public ReportByCategoryDao reportByCategoryDao;
    public ReportByCategoryModelToReportByCategoryDtoConverter reportByCategoryDtoConverter;

    public ReportByCategoryService(ReportByCategoryDao reportByCategoryDao, ReportByCategoryModelToReportByCategoryDtoConverter reportByCategoryModelToReportByCategoryDtoConverter) {
        this.reportByCategoryDao = reportByCategoryDao;
        this.reportByCategoryDtoConverter = reportByCategoryModelToReportByCategoryDtoConverter;
    }

    public List<ReportByCategoryDTO> viewReportCategory(long idUser, String startDay, String endDay) throws SQLException {
        List<ReportByCategoryDTO> reportByCategoryDTOS;
        List<ReportByCategoryModel> reportByCategoryModels = reportByCategoryDao.reportByCategory(idUser, startDay, endDay);
        if (reportByCategoryModels == null) {
            return reportByCategoryDao.reportByCategory(idUser, startDay, endDay)
                    .stream()
                    .map(reportByCategoryDtoConverter::convert)
                    .collect(Collectors.toList());
        }

        reportByCategoryDTOS = reportByCategoryModels.stream().map(item -> reportByCategoryDtoConverter.convert(item)).collect(Collectors.toList());
        return reportByCategoryDTOS;
    }
}
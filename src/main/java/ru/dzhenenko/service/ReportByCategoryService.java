package ru.dzhenenko.service;

import org.springframework.stereotype.Service;
import ru.dzhenenko.converter.Converter;
import ru.dzhenenko.dao.ReportByCategoryDao;
import ru.dzhenenko.dao.ReportByCategoryModel;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportByCategoryService {

    public ReportByCategoryDao reportByCategoryDao;
    public Converter<ReportByCategoryModel, ReportByCategoryDTO> reportByCategoryDtoConverter;

    public ReportByCategoryService(ReportByCategoryDao reportByCategoryDao, Converter<ReportByCategoryModel, ReportByCategoryDTO> reportByCategoryModelToReportByCategoryDtoConverter) {
        this.reportByCategoryDao = reportByCategoryDao;
        this.reportByCategoryDtoConverter = reportByCategoryModelToReportByCategoryDtoConverter;
    }

    public List<ReportByCategoryDTO> viewReportCategory(long idUser, String startDay, String endDay) throws SQLException {
        return reportByCategoryDao.reportByCategory(idUser, startDay, endDay)
                .stream()
                .map(reportByCategoryDtoConverter::convert)
                .collect(Collectors.toList());
    }
}

package ru.dzhenenko.service;

import org.springframework.stereotype.Service;
import ru.dzhenenko.converter.Converter;
import ru.dzhenenko.dao.ReportByCategoryDao;
import ru.dzhenenko.entity.ReportByCategory;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportByCategoryService {

    public ReportByCategoryDao reportByCategoryDao;
    public Converter<ReportByCategory, ReportByCategoryDTO> reportByCategoryDtoConverter;

    public ReportByCategoryService(ReportByCategoryDao reportByCategoryDao, Converter<ReportByCategory, ReportByCategoryDTO> reportByCategoryModelToReportByCategoryDtoConverter) {
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

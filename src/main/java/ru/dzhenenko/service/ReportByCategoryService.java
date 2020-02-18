package ru.dzhenenko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.api.converter.Converter;
import ru.dzhenenko.dao.ReportByCategoryDao;
import ru.dzhenenko.entity.ReportByCategory;
import ru.dzhenenko.repository.ServiceReportByCategoryRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportByCategoryService {

    public ReportByCategoryDao reportByCategoryDao;
    private final Converter<ReportByCategory, ReportByCategoryDTO> reportByCategoryDtoConverter;
    private final ServiceReportByCategoryRepository repository;

    public List<ReportByCategoryDTO> viewReportCategory(long idUser, String startDay, String endDay) throws SQLException {
        return reportByCategoryDao.reportByCategory(idUser, startDay, endDay)
                .stream()
                .map(reportByCategoryDtoConverter::convert)
                .collect(Collectors.toList());
    }
}

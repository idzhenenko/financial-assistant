package ru.dzhenenko.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.api.converter.Converter;
import ru.dzhenenko.entity.ReportByCategory;
import ru.dzhenenko.repository.ServiceReportByCategoryRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportByCategoryService {

    private final ServiceReportByCategoryRepository serviceReportByCategoryRepository;
    private final Converter<ReportByCategory, ReportByCategoryDTO> reportByCategoryDtoConverter;

    public List<ReportByCategoryDTO> viewReportCategory(long idUser, String startDay, String endDay) throws SQLException {

        List<ReportByCategoryDTO> reportByCategoryDTOS = new ArrayList<>();

        List<ReportByCategory> reportByCategories = serviceReportByCategoryRepository.findByUserIdAndStartDateAndEndDate(
                idUser, startDay, endDay);

        if (!reportByCategories.isEmpty()) {
            reportByCategoryDTOS = reportByCategories.stream().map(reportByCategoryDtoConverter::convert)
                    .collect(Collectors.toList());
        }
        return reportByCategoryDTOS;

    }
}

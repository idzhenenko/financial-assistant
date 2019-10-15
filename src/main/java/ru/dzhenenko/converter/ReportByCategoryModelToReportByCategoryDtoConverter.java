package ru.dzhenenko.converter;

import ru.dzhenenko.dao.ReportByCategoryModel;
import ru.dzhenenko.service.ReportByCategoryDTO;

import java.sql.Timestamp;

public class ReportByCategoryModelToReportByCategoryDtoConverter implements Converter<ReportByCategoryModel, ReportByCategoryDTO>{
    @Override
    public ReportByCategoryDTO convert(ReportByCategoryModel source) {
        ReportByCategoryDTO reportByCategoryDTO = new ReportByCategoryDTO();
        reportByCategoryDTO.setName(source.getName());
        reportByCategoryDTO.setAmount(source.getAmount());
        return reportByCategoryDTO;
    }
}

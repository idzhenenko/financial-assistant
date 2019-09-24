package ru.dzhenenko.converter;

import ru.dzhenenko.dao.ReportByCategoryModel;
import ru.dzhenenko.service.ReportByCategoryDTO;

public class ReportByCategoryModelToReportByCategoryDtoConverter implements Converter  <ReportByCategoryModel, ReportByCategoryDTO>{

    @Override
    public ReportByCategoryDTO convert(ReportByCategoryModel source) {
        ReportByCategoryDTO reportByCategoryDTO = new ReportByCategoryDTO();
        reportByCategoryDTO.setName(source.getName());
        reportByCategoryDTO.setAmmount(source.getAmmount());
        return reportByCategoryDTO;
    }
}

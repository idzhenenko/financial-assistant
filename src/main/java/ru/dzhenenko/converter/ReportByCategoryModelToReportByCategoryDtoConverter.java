package ru.dzhenenko.converter;

import org.springframework.stereotype.Service;
import ru.dzhenenko.dao.ReportByCategoryModel;
import ru.dzhenenko.service.ReportByCategoryDTO;

@Service
public class ReportByCategoryModelToReportByCategoryDtoConverter implements Converter<ReportByCategoryModel, ReportByCategoryDTO> {
    @Override
    public ReportByCategoryDTO convert(ReportByCategoryModel source) {
        ReportByCategoryDTO reportByCategoryDTO = new ReportByCategoryDTO();
        reportByCategoryDTO.setName(source.getName());
        reportByCategoryDTO.setAmount(source.getAmount());
        return reportByCategoryDTO;
    }
}

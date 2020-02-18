package ru.dzhenenko.api.converter;

import org.springframework.stereotype.Service;
import ru.dzhenenko.entity.ReportByCategory;
import ru.dzhenenko.service.ReportByCategoryDTO;

@Service
public class ReportByCategoryModelToReportByCategoryDtoConverter implements Converter<ReportByCategory, ReportByCategoryDTO> {
    @Override
    public ReportByCategoryDTO convert(ReportByCategory source) {
        ReportByCategoryDTO reportByCategoryDTO = new ReportByCategoryDTO();
        reportByCategoryDTO.setName(source.getName());
        reportByCategoryDTO.setAmount(source.getAmount());
        return reportByCategoryDTO;
    }
}

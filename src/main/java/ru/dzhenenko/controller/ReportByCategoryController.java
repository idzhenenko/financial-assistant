package ru.dzhenenko.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dzhenenko.json.AccountRequest;
import ru.dzhenenko.json.AccountResponse;
import ru.dzhenenko.json.ReportByCategoryRequest;
import ru.dzhenenko.json.ReportByCategoryResponse;
import ru.dzhenenko.service.AccountDTO;
import ru.dzhenenko.service.AccountService;
import ru.dzhenenko.service.ReportByCategoryDTO;
import ru.dzhenenko.service.ReportByCategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service("/reportByCategory")
@RequiredArgsConstructor
public class ReportByCategoryController implements SecureController<ReportByCategoryRequest, List<ReportByCategoryResponse>> {
    private final ReportByCategoryService reportByCategoryService;

    @Override
    public List<ReportByCategoryResponse> handle(ReportByCategoryRequest request, Long userId) throws Exception {
        List<ReportByCategoryDTO> reportByCategoryDTO = reportByCategoryService.viewReportCategory(request.getId(), request.getStartDay(), request.getEndDay());
     return reportByCategoryDTO.stream()
             .map(reportByCategoryDTO1 -> new ReportByCategoryResponse(reportByCategoryDTO1.getName(), reportByCategoryDTO1.getAmount()))
             .collect(Collectors.toList());
    }

    @Override
    public Class<ReportByCategoryRequest> getRequestClass() {
        return ReportByCategoryRequest.class;
    }
}
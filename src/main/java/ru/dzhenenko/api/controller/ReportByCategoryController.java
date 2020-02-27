package ru.dzhenenko.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dzhenenko.api.json.ReportByCategoryRequest;
import ru.dzhenenko.api.json.ReportByCategoryResponse;
import ru.dzhenenko.service.ReportByCategoryDTO;
import ru.dzhenenko.service.ReportByCategoryService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ReportByCategoryController {
    private final ReportByCategoryService reportByCategoryService;

    @PostMapping("/view-report")
    public @ResponseBody
    ResponseEntity<List<ReportByCategoryResponse>> viewListAccount(@RequestBody @Valid ReportByCategoryRequest request,
                                                                   HttpServletRequest httpServletRequest) throws SQLException {

        Long userId = (Long) httpServletRequest.getSession().getAttribute("userId");

        /*List<ReportByCategoryDTO> reportByCategoryDTOS = reportByCategoryService.viewReportCategory(
                request.getId(), request.getStartDay(), request.getEndDay());

        return ok(reportByCategoryDTOS.stream()
                .map(reportByCategoryDTOS1 -> new ReportByCategoryResponse(reportByCategoryDTOS1.getName(),
                        reportByCategoryDTOS1.getAmount()))
                .collect(Collectors.toList()));*/
        return  null;
    }
}
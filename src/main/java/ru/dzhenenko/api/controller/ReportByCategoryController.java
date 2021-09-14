package ru.dzhenenko.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dzhenenko.api.json.ReportByCategoryRequest;
import ru.dzhenenko.api.json.ReportByCategoryResponse;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.ReportByCategoryDTO;
import ru.dzhenenko.service.ReportByCategoryService;
import ru.dzhenenko.service.UserDTO;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReportByCategoryController {
    private final ReportByCategoryService reportByCategoryService;
    private final AuthService authService;

    @PostMapping("/view-report")
    public @ResponseBody
    ResponseEntity<List<ReportByCategoryResponse>> viewListReport(@RequestBody @Valid ReportByCategoryRequest request) throws SQLException {
        UserDTO userDTO = authService.currentUser();
        if (userDTO == null) {
            return status(HttpStatus.UNAUTHORIZED).build();
        }
        List<ReportByCategoryDTO> reportByCategoryDTOS = reportByCategoryService.viewReportCategory(userDTO.getId(),
                request.getStartDay(), request.getEndDay());
        return ok(reportByCategoryDTOS.stream()
                .map(reportByCategoryDTOS1 -> new ReportByCategoryResponse(reportByCategoryDTOS1.getName(),
                        reportByCategoryDTOS1.getAmount()))
                .collect(Collectors.toList()));
    }
}
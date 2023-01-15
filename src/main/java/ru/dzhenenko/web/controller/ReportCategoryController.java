package ru.dzhenenko.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.dzhenenko.service.AuthService;
import ru.dzhenenko.service.ReportByCategoryDTO;
import ru.dzhenenko.service.ReportByCategoryService;
import ru.dzhenenko.service.UserDTO;
import ru.dzhenenko.web.form.ReportForm;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReportCategoryController {
    private final ReportByCategoryService reportByCategoryService;
    private final AuthService authService;

    @GetMapping("/report-account-category")
    public String getReport(Model model) {
        model.addAttribute("form", new ReportForm());
        return "categoryReportGet";

    }

    @PostMapping("/report-account-category")
    public String postReport(@ModelAttribute("form") @Valid ReportForm form, BindingResult result, Model model) {
        if (!result.hasErrors()) {
            UserDTO userDTO = authService.currentUser();
            if (userDTO == null) {
                return "redirect:/login-form";
            }

            List<ReportByCategoryDTO> report = reportByCategoryService.viewReportCategory(
                    userDTO.getId(),
                    form.getStartDay(),
                    form.getEndDay());
            if (report.isEmpty()) {
                model.addAttribute("msg", "Нет данных");
            } else {
                model.addAttribute("report", report)
                        .addAttribute("startDay", form.getStartDay())
                        .addAttribute("endDay", form.getEndDay());
            }
        }
        return "categoryReport";
    }
}
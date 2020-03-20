package ru.dzhenenko.web.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ReportForm {
    @NotEmpty
    private String startDay;

    @NotEmpty
    private String endDay;
}

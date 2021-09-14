package ru.dzhenenko.api.json;

import lombok.Data;

@Data
public class ReportByCategoryRequest {
    private String startDay;
    private String endDay;
}
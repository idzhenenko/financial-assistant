package ru.dzhenenko.json;

import lombok.Data;

@Data
public class ReportByCategoryRequest {
    private long id;
    private String startDay;
    private String endDay;
}
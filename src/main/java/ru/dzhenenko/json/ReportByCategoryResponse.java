package ru.dzhenenko.json;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReportByCategoryResponse {
    private String name;
    private long amount;
}
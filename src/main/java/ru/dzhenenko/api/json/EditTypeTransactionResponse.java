package ru.dzhenenko.api.json;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditTypeTransactionResponse {
    private long id;
    private String name;
}
package ru.dzhenenko.json;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteTypeTransactionResponse {
    private long id;
    private String name;
}
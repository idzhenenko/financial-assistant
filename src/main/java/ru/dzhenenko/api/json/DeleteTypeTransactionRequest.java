package ru.dzhenenko.api.json;

import lombok.Data;

@Data
public class DeleteTypeTransactionRequest {
    private long id;
    private String name;
}
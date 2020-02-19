package ru.dzhenenko.api.json;

import lombok.Data;

@Data
public class AddTypeTransactionRequest {
    private long id;
    private String name;
}
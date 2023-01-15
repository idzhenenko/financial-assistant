package ru.dzhenenko.api.json;

import lombok.Data;

@Data
public class EditTypeTransactionRequest {
    private long id;
    private String name;
}

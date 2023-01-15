package ru.dzhenenko.api.json;

import lombok.Data;

@Data
public class TransactionRequest {
    private long sourceAccount;
    private long targetAccount;
    private String createDate;
    private long typeTransaction;
    private long amount;
    private long idCategory;
}

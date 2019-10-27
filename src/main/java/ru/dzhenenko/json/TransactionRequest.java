package ru.dzhenenko.json;

import lombok.Data;

@Data
public class TransactionRequest {
    private long id;
    private long sourceAccount;
    private long targetAccount;
    private String createDate;
    private long typeTransaction;
    private long amount;
    private long idCategory;
}
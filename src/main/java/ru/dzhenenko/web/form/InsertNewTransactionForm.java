package ru.dzhenenko.web.form;

import lombok.Data;

@Data
public class InsertNewTransactionForm {
    private long sourceAccount;
    private long targetAccount;
    private String createDate;
    private long typeTransaction;
    private long idCategory;
    private long amount;
}

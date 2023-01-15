package ru.dzhenenko.api.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.dzhenenko.entity.Account;
import ru.dzhenenko.entity.AccountType;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TransactionResponse {
    private long id;
    private long sourceAccount;
    private long targetAccount;
    private String createDate;
    private long typeTransaction;
    private long amount;

    public TransactionResponse(Long id, Account sourceAccount, Account targetAccount, LocalDate createDate, AccountType typeTransaction, Long amount) {

    }
}

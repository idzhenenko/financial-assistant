package ru.dzhenenko.web.form;

import lombok.Data;
import ru.dzhenenko.entity.Account;
import ru.dzhenenko.entity.AccountType;
import ru.dzhenenko.entity.Category;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class InsertNewTransactionForm {

    private long sourceAccount;

    private long targetAccount;

    private String createDate;

    private long typeTransaction;

    private long idCategory;

    private long amount;
}

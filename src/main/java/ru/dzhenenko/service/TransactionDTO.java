package ru.dzhenenko.service;

import java.util.Objects;

public class TransactionDTO {
    private long id;
    private long sourceAccount;
    private long targetAccount;
    private String createDate;
    private long typeTransaction;
    private long amount;

    public TransactionDTO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(long sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public long getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(long targetAccount) {
        this.targetAccount = targetAccount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public long getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(long typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", sourceAccount=" + sourceAccount +
                ", targetAccount=" + targetAccount +
                ", createDate='" + createDate + '\'' +
                ", idTypeTransaction=" + typeTransaction +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDTO that = (TransactionDTO) o;
        return id == that.id &&
                sourceAccount == that.sourceAccount &&
                targetAccount == that.targetAccount &&
                typeTransaction == that.typeTransaction &&
                amount == that.amount &&
                Objects.equals(createDate, that.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceAccount, targetAccount, createDate, typeTransaction, amount);
    }
}
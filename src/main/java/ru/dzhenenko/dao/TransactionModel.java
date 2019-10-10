package ru.dzhenenko.dao;

import java.util.Objects;

public class TransactionModel {
    private long id;
    private long sourceAccount;
    private long targetAccount;
    private String createDate;
    private long TypeTransaction;
    private long amount;

    public TransactionModel() {

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
        return TypeTransaction;
    }

    public void setTypeTransaction(long TypeTransaction) {
        this.TypeTransaction = TypeTransaction;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "id=" + id +
                ", sourceAccount=" + sourceAccount +
                ", targetAccount=" + targetAccount +
                ", createDate='" + createDate + '\'' +
                ", idTypeTransaction=" + TypeTransaction +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionModel that = (TransactionModel) o;
        return id == that.id &&
                sourceAccount == that.sourceAccount &&
                targetAccount == that.targetAccount &&
                TypeTransaction == that.TypeTransaction &&
                amount == that.amount &&
                Objects.equals(createDate, that.createDate);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, sourceAccount, targetAccount, createDate, TypeTransaction, amount);
    }
}

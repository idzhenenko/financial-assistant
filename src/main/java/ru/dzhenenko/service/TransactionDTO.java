package ru.dzhenenko.service;

import lombok.Data;

import java.util.Objects;

@Data
public class TransactionDTO {
    private long id;
    private long sourceAccount;
    private long targetAccount;
    private String createDate;
    private long typeTransaction;
    private long amount;
    private long idCategory;

    public TransactionDTO() {

    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "id=" + id +
                ", sourceAccount=" + sourceAccount +
                ", targetAccount=" + targetAccount +
                ", createDate='" + createDate + '\'' +
                ", typeTransaction=" + typeTransaction +
                ", amount=" + amount +
                ", idCategory=" + idCategory +
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

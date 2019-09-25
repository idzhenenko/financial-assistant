package ru.dzhenenko.service;

import ru.dzhenenko.dao.ReportByCategoryModel;

import java.sql.Timestamp;
import java.util.Objects;

public class ReportByCategoryDTO {
    private long id;
    private String name;
    private long amount;
    private long sourceAccount;
    private long targetAccount;
    private Timestamp createDate;

    public ReportByCategoryDTO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "ReportByCategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", sourceAccount=" + sourceAccount +
                ", targetAccount=" + targetAccount +
                ", createDate=" + createDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportByCategoryDTO that = (ReportByCategoryDTO) o;
        return id == that.id &&
                amount == that.amount &&
                sourceAccount == that.sourceAccount &&
                targetAccount == that.targetAccount &&
                Objects.equals(name, that.name) &&
                Objects.equals(createDate, that.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amount, sourceAccount, targetAccount, createDate);
    }
}
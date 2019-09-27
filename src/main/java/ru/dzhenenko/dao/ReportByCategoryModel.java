package ru.dzhenenko.dao;

import java.sql.Timestamp;
import java.util.Objects;

public class ReportByCategoryModel {
    private String name;
    private long amount;

    public ReportByCategoryModel() {

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

    @Override
    public String toString() {
        return "ReportByCategoryModel{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportByCategoryModel that = (ReportByCategoryModel) o;
        return amount == that.amount &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount);
    }
}
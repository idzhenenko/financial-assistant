package ru.dzhenenko.service;

import ru.dzhenenko.dao.ReportByCategoryModel;

import java.sql.Timestamp;
import java.util.Objects;

public class ReportByCategoryDTO {
    private String name;
    private long amount;

    public ReportByCategoryDTO() {

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
        return name +
                " = " + amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportByCategoryDTO that = (ReportByCategoryDTO) o;
        return amount == that.amount &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount);
    }
}
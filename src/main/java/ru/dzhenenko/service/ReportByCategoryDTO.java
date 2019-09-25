package ru.dzhenenko.service;

import ru.dzhenenko.dao.ReportByCategoryModel;

import java.util.Objects;

public class ReportByCategoryDTO {
    String name;
    long ammount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmmount() {
        return ammount;
    }

    public void setAmmount(long ammount) {
        this.ammount = ammount;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "name='" + name + '\'' +
                ", ammount=" + ammount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReportByCategoryModel)) return false;
        ReportByCategoryModel that = (ReportByCategoryModel) o;
        return getAmmount() == that.getAmmount() &&
                Objects.equals(getName(), that.getName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAmmount());
    }
}


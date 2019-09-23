package ru.dzhenenko.dao;

import java.sql.Timestamp;
import java.util.Objects;

public class ReportByCategoryModel {
        String name;
        long ammount;
        long source_account;
        long target_account;
        Timestamp create_date;

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public long getSource_account() {
        return source_account;
    }

    public void setSource_account(long source_account) {
        this.source_account = source_account;
    }

    public long getTarget_account() {
        return target_account;
    }

    public void setTarget_account(long target_account) {
        this.target_account = target_account;
    }

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


    public void setId(long user_id) {
    }
}


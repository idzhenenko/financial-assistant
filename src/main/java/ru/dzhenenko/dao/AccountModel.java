package ru.dzhenenko.dao;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountModel {
    private long id;
    private String name;
    private long balance;
    private long userId;

    public AccountModel() {
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

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void getInt(String id_users) {
    }

    public void getId(int id) {
    }

    public void getInt(int balance) {
    }

    public void getBalance(int balance) {
    }

    public void getName(String name) {
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountModel)) return false;
        AccountModel that = (AccountModel) o;
        return getId() == that.getId() &&
                getBalance() == that.getBalance() &&
                getUserId() == that.getUserId() &&
                getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBalance(), getUserId());
    }

    @Override
    public String toString() {
        return "AccountModel" +
                " id = " + id +
                ", name = '" + name + '\'' +
                ", balance = " + balance +
                ", userId = " + userId;
    }

    public void getUserId(long userId) {
        this.userId = userId;
    }

}
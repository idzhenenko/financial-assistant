package ru.dzhenenko.dao;

public class AccountModel {
    private long id;
    private String name;
    private int balance;
    private long userId;

    public AccountModel(long id, String name, int balance, long userId) {
        this.id = id;
        this.name = name;
        this.balance = balance;
        this.userId = userId;
    }

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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}


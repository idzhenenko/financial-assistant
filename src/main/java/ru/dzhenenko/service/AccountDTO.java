package ru.dzhenenko.service;

import ru.dzhenenko.dao.AccountModel;

public class AccountDTO {
    private long id;
    private String name;
    private int balance;
    private long userId;

    public AccountDTO(AccountModel accountModel) {
        this.id = accountModel.getId();
        this.name = accountModel.getName();
        this.balance = accountModel.getBalance();
        this.userId = accountModel.getUserId();
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

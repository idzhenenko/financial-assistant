package ru.dzhenenko.service;


import java.util.Objects;

public class AccountDTO {
    private long id;
    private String name;
    private long balance;
    private long userId;

    public AccountDTO(long id, String name, int balance, long userId) {

    }
    public AccountDTO() {
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getBalance(), getUserId());
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

    @Override
    public String toString() {
        return "AccountDTO" +
                " id= " + id +
                ", name = '" + name + '\'' +
                ", balance = " + balance +
                ", userId = " + userId;
    }

}

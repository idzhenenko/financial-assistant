package ru.dzhenenko.api.json;

import lombok.Data;

@Data
public class AccountRequest {
    private long id;
    private String name;
    private long balance;
    private long userId;
}
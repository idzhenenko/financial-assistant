package ru.dzhenenko.api.json;

import lombok.Data;

@Data
public class DeleteAccountRequest {
    private String name;
    private int balance;
    private long id;
}
package ru.dzhenenko.json;

import lombok.Data;

@Data
public class AddAccountRequest {
    private String name;
    private int balance;
    private long id;
}
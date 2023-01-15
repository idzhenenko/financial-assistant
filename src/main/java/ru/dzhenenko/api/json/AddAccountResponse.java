package ru.dzhenenko.api.json;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddAccountResponse {
    private long id;
    private String name;
    private long balance;
    private long userId;
}

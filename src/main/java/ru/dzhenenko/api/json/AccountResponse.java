package ru.dzhenenko.api.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.dzhenenko.entity.User;
import ru.dzhenenko.service.AccountDTO;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountResponse {
    private long id;
    private String name;
    private long balance;
    private long userId;

    public AccountResponse(Long id, String name, Integer balance, User user) {
    }
}
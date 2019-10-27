package ru.dzhenenko.json;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
    private String name;
}
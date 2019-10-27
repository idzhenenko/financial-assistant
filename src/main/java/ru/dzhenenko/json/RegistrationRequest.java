package ru.dzhenenko.json;

import lombok.Data;

@Data
public class RegistrationRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
}
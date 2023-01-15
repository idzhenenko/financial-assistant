package ru.dzhenenko.api.json;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
}

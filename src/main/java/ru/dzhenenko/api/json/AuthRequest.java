package ru.dzhenenko.api.json;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AuthRequest {
    @Email
    @NotNull
    private String email;

    @NotNull
    @Size(min =5, max = 30)
    private String password;

    private String name;
}
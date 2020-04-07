package ru.dzhenenko.repository;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.dzhenenko.secuity.UserRole;

import java.util.Set;

@Data
@Accessors(chain = true)
public class ServiceUserFilter {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private UserRole userRole;
}

package ru.dzhenenko.web.form;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Accessors(chain = true)
@Data
public class AddUserForm {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String phone;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}

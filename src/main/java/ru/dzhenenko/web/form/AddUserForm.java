package ru.dzhenenko.web.form;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
    @Size(min = 2, max = 30)
    private String password;

}

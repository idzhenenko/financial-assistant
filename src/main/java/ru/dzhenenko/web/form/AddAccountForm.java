package ru.dzhenenko.web.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddAccountForm {

    private Integer balance;

    @NotEmpty
    private String name;
}

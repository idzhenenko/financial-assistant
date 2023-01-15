package ru.dzhenenko.web.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ViewAccountForm {
    private long id;
    private Integer balance;
    @NotEmpty
    private String name;
}
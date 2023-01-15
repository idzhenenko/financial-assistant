package ru.dzhenenko.web.form;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Accessors(chain = true)
@Data
public class AddAccountForm {
    private Integer balance;
    @NotEmpty
    private String name;
}
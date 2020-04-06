package ru.dzhenenko.web.form;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class AddTypeAccountForm {

    private long id;

    private String name;
}

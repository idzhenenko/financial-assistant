package ru.dzhenenko.service;


import ru.dzhenenko.dao.AccountTypeModel;

import java.util.Objects;

public class AccountTypeDTO {
    private long id;
    private String name;

    public AccountTypeDTO() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountTypeDTO)) return false;
        AccountTypeDTO that = (AccountTypeDTO) o;
        return getId() == that.getId() &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
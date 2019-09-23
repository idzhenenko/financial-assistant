package ru.dzhenenko.dao;

import java.util.Objects;

public class UserModel {
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserModel)) return false;
        UserModel userModel = (UserModel) o;
        return getId() == userModel.getId() &&
                Objects.equals(getFirstName(), userModel.getFirstName()) &&
                Objects.equals(getLastName(), userModel.getLastName()) &&
                Objects.equals(getPhone(), userModel.getPhone()) &&
                Objects.equals(getEmail(), userModel.getEmail()) &&
                Objects.equals(getPassword(), userModel.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getPhone(), getEmail(), getPassword());
    }
}


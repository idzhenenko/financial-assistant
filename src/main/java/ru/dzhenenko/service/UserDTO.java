package ru.dzhenenko.service;

import lombok.Data;

import java.util.Objects;

@Data
public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getFirstName(), userDTO.getFirstName()) &&
                Objects.equals(getLastName(), userDTO.getLastName()) &&
                Objects.equals(getPhone(), userDTO.getPhone()) &&
                Objects.equals(getEmail(), userDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getPhone(), getEmail());
    }
}

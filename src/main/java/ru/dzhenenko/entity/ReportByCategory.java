package ru.dzhenenko.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
public class ReportByCategory {
    @Id
    private String name;

    private int amount;

    @Override
    public String toString() {
        return "ReportByCategory{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}

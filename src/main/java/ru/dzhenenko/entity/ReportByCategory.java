package ru.dzhenenko.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportByCategory report = (ReportByCategory) o;
        return amount == report.amount &&
                Objects.equals(name, report.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount);
    }
}
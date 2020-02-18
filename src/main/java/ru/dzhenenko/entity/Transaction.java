package ru.dzhenenko.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "transaction")
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_account")
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "target_account")
    private Account targetAccount;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "create_date")
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "id_type_transaction")
    private AccountType typeTransaction;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "id_tran_to_id_category",
            joinColumns = @JoinColumn(name = "id_transaction", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_category", referencedColumnName = "id")
    )
    private List<Category> categorys;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", sourceAccount=" + sourceAccount +
                ", targetAccount=" + targetAccount +
                ", amount=" + amount +
                ", createDate='" + createDate + '\'' +
                ", typeTransaction=" + typeTransaction +
                '}';
    }

    public Long getAmount() {
        return amount;
    }
}
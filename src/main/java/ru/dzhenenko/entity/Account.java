package ru.dzhenenko.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "account")
@NamedQueries({
        @NamedQuery(name = "Account.findByUserId", query = "SELECT a FROM Account AS a WHERE a.id = :id"),
        @NamedQuery(name = "Account.viewAccountUser", query = "SELECT a FROM Account AS a WHERE a.user = :user")
})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "balance")
    private Integer balance;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_users")
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account")
    private List<Transaction> fromTransactions;


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_account")
    private List<Transaction> toTransactions;

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", name='" + name + '\'' +
                '}';
    }
}
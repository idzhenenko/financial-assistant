package ru.dzhenenko.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.dzhenenko.api.json.AuthRequest;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
@NamedQueries({
        @NamedQuery(name = "Users.getByEmailAndPassword", query = "SELECT u FROM User AS u WHERE u.email = :email and u.password = :password"),
        @NamedQuery(name = "Users.getById", query = "SELECT u FROM User AS u WHERE u.id = :id")
})
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Account> accounts;


}

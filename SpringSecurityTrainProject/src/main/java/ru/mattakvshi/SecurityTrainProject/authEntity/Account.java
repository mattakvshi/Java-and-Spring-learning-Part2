package ru.mattakvshi.SecurityTrainProject.authEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.WeakHashMap;

@Entity
@Data
@Table(name = "user_table")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;

    @Column(unique = true) //Правило уникальности логина
    private String login;

    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id") // Связываем с сущьностью роли как многие к одному, много пользователей могут иметь одну и ту же роль, но у каждого пользователя только одна роль
    private RoleEntity roleEntity;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "account_Id")
    private List<MyListAcc> myListAcc;


}

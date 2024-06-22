package ru.mattakvshi.SecurityTrainProject.authEntity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "role_tebel")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

}

package ru.mattakvshi.SecurityTrainProject.authEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
@NoArgsConstructor
@Table(name = "my_list_acc")
public class MyListAcc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @JsonManagedReference
    private Account account;
}
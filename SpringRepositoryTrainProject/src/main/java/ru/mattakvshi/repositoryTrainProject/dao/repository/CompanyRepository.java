package ru.mattakvshi.repositoryTrainProject.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mattakvshi.repositoryTrainProject.company.ITCompany;

@Repository
public interface CompanyRepository  extends CrudRepository<ITCompany, Long> {
    //И для работы с этой компанией нам не нужно даже писать никакой реализации, нам достаточно тех методов,
    //что прописанны в CrudRepository, хотя казалось бы там тодже нет их реализации, но Spring сам создаст
    // всю необходимую реализацию при поднятии контекста и создании нашего CompanyRepository
}

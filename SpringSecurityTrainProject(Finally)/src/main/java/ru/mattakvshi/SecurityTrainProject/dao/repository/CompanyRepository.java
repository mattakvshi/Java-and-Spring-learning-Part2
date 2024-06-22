package ru.mattakvshi.SecurityTrainProject.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mattakvshi.SecurityTrainProject.entity.company.ITCompany;
import ru.mattakvshi.SecurityTrainProject.entity.employee.Employee;
import ru.mattakvshi.SecurityTrainProject.entity.employee.ITRole;

import java.util.List;

@Repository
public interface CompanyRepository  extends CrudRepository<ITCompany, Long> {
    //И для работы с этой компанией нам не нужно даже писать никакой реализации, нам достаточно тех методов,
    //что прописанны в CrudRepository, хотя казалось бы там тодже нет их реализации, но Spring сам создаст
    // всю необходимую реализацию при поднятии контекста и создании нашего CompanyRepository

    //Находим по директору
    List<ITCompany> findByDirector(Employee<ITRole> director);
}

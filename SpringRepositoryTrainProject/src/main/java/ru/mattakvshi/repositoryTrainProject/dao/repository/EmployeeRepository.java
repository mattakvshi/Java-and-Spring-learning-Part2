package ru.mattakvshi.repositoryTrainProject.dao.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mattakvshi.repositoryTrainProject.company.ITCompany;
import ru.mattakvshi.repositoryTrainProject.company.employee.Employee;
import ru.mattakvshi.repositoryTrainProject.company.employee.ITRole;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee<ITRole>, Long> {

    //Реализация через правильный нейминг, в таком случае Spring сам понимает какого рода запрос нужно сделать (ПРОСТО МАГИЯ КАКАЯ-ТО)

    List<Employee> findByRoleAndCompany(ITRole role, ITCompany company);
    List<Employee> findByRoleAndCompanyId(ITRole role, Long company_id);

    List<Employee> findByAgeAndCompany(int age, ITCompany company);
    List<Employee> findByAgeAndCompanyId(int age, Long company_id);

    //РЕАЛИЗАЦИЯ ВРУЧНУЮ ЧЕРЕЗ Hibernate Query Language

    @Query("SELECT e FROM Employee e WHERE e.role = :role AND e.company = :company")
    List<Employee> findByRoleAndCompanyQuery(ITRole role, ITCompany company);

    @Query("SELECT e FROM Employee e WHERE e.age = :age AND e.company = :company")
    List<Employee> findByAgeAndCompanyQuery(int age, ITCompany company);
}

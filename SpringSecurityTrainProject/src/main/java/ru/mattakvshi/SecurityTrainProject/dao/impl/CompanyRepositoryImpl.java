package ru.mattakvshi.SecurityTrainProject.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.mattakvshi.SecurityTrainProject.company.ITCompany;
import ru.mattakvshi.SecurityTrainProject.company.employee.Developer;
import ru.mattakvshi.SecurityTrainProject.company.employee.Employee;
import ru.mattakvshi.SecurityTrainProject.company.employee.ITRole;
import ru.mattakvshi.SecurityTrainProject.company.employee.PM;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import ru.mattakvshi.SecurityTrainProject.dao.CompanyDAO;
import ru.mattakvshi.SecurityTrainProject.dao.EmployeeDAO;

import java.util.List;

@Slf4j
@Repository
public class CompanyRepositoryImpl implements CompanyDAO, EmployeeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Long create(ITCompany company) {
        entityManager.persist(company);
        entityManager.flush();
        return company.getId();
    }

    @Override
    public ITCompany find(long id) {
        return entityManager.find(ITCompany.class, id);
    }

    @Override
    @Transactional
    public void addDeveloper(Developer developer) {
        entityManager.persist(developer);
    }

    @Override
    @Transactional
    public void addPM(PM pm) {
        entityManager.persist(pm);
    }

    @Override
    @Transactional
    public Employee<ITRole> getEmployerByIndex(long index) {
        Developer developer =  entityManager.find(Developer.class, index);
        log.info("persistence contains object " + entityManager.contains(developer));
        entityManager.detach(developer);
        log.info("persistence contains object " + entityManager.contains(developer));
        return developer;
    }

    @Override
    public List<Employee> getEmployerByRole(ITRole role, long company_id) {
        log.info("123");
        List<Employee> employees = entityManager.createQuery(
                        "select e from Employee e where e.role = :role and e.company = :company", Employee.class)
                .setParameter("role", role)
                .setParameter("company", find(company_id))
                .getResultList();

        return employees;
    }

    @Override
    public List<Employee> getEmployerByAge(int age, long company_id) {
        log.info("123");
        List<Employee> employees = entityManager.createQuery(
                        "SELECT e FROM Employee e WHERE e.age = :age AND e.company = :company", Employee.class)
                .setParameter("age", age)
                .setParameter("company", find(company_id))
                .getResultList();
        return employees;
    }
}

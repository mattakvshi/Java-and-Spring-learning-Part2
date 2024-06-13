package ru.mattakvshi.hibernateTrainProject.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.mattakvshi.hibernateTrainProject.company.ITCompany;
import ru.mattakvshi.hibernateTrainProject.company.employee.Developer;
import ru.mattakvshi.hibernateTrainProject.company.employee.Employee;
import ru.mattakvshi.hibernateTrainProject.company.employee.ITRole;
import ru.mattakvshi.hibernateTrainProject.company.employee.PM;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Repository
public class CompanyRepository implements CompanyDAO, EmployeeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Integer create(ITCompany company) {
        //entityManager.persist(company.getDirector());
        // entityManager.flush();
        entityManager.persist(company);
        entityManager.flush();
        return company.getId();
    }

    @Override
    public ITCompany find(int id) {
        return entityManager.find(ITCompany.class, id);
    }

    @Override
    @Transactional
    public void addDeveloper(Developer developer) {
        entityManager.persist(developer);
//        entityManager.flush();
//        entityManager.detach(developer);
//        developer.setAge(30);
//        developer.setRole(ITRole.QA);
//        entityManager.merge(developer);
    }

    @Override
    @Transactional
    public void addPM(PM pm) {
        entityManager.persist(pm);
    }

    @Override
    @Transactional
    public Employee<ITRole> getEmployerByIndex(int index) {
        Developer developer =  entityManager.find(Developer.class, index);
        log.info("persistence contains object " + entityManager.contains(developer));
        entityManager.detach(developer);
        log.info("persistence contains object " + entityManager.contains(developer));
        return developer;
    }

    @Override
    public List<Employee> getEmployerByRole(ITRole role, int company_id) {
        List<Employee> employees = entityManager.createQuery(
                        "select e from Employee e where e.role = :role and e.company = :company", Employee.class)
                .setParameter("role", role)
                .setParameter("company", find(company_id))
                .getResultList();

        return employees;
    }

    @Override
    public List<Employee> getEmployerByAge(int age, int company_id) {
//        List<Employee> employees = entityManager.createNativeQuery(
//                "SELECT e FROM employees WHERE e.age = :age AND e.company = :company;", Employee.class)
//                .setParameter("age", age)
//                .setParameter("company", getCompany(company_id))
//                .getResultList();

        List<Employee> employees = entityManager.createQuery(
                        "SELECT e FROM Employee e WHERE e.age = :age AND e.company = :company", Employee.class)
                .setParameter("age", age)
                .setParameter("company", find(company_id))
                .getResultList();
        return employees;
    }
}

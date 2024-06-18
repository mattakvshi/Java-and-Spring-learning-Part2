package ru.mattakvshi.hibernateTrainProject.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.mattakvshi.hibernateTrainProject.company.ITCompany;
import ru.mattakvshi.hibernateTrainProject.company.employee.Developer;
import ru.mattakvshi.hibernateTrainProject.company.employee.Employee;
import ru.mattakvshi.hibernateTrainProject.company.employee.ITRole;
import ru.mattakvshi.hibernateTrainProject.company.employee.PM;


import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import java.util.List;

@Slf4j
@Repository
public class CompanyRepository implements CompanyDAO, EmployeeDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    //Самое обычное распространение транзакции, стоит и так по умолчанию даже еслии не прописать, говорит о том что создаём транзакцию если уже не создана.
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer create(ITCompany company) {
        entityManager.persist(company);
        entityManager.flush();
        return company.getId();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ITCompany find(int id) {
       ITCompany company = entityManager.find(ITCompany.class, id);
       //Мы закрываем сущьность через entityManager тоже как вариант использования блокировок
       //entityManager.lock(company, LockModeType.PESSIMISTIC_READ); //
       return company;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW) // Всегда создаём новую транзакцию при входе в метод, ранее созданные приостанавливаются до возврата из метода.
    public void addDeveloper(Developer developer) {
        entityManager.persist(developer);
    }

    @Override
    @Transactional(propagation = Propagation.MANDATORY) // Всегда используется существующая, и кидается испключение если её нет.
    public void addPM(PM pm) {
        entityManager.persist(pm);
    }

    @Override
    @Transactional(propagation = Propagation.NEVER) // Запрещает использование транзакции, если транзакция существует выбрасывается исключение.
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
        List<Employee> employees = entityManager.createQuery(
                        "SELECT e FROM Employee e WHERE e.age = :age AND e.company = :company", Employee.class)
                .setParameter("age", age)
                .setParameter("company", find(company_id))
                .getResultList();
        return employees;
    }
}

package ru.mattakvshi.hibernateTrainProject.dao;

import ru.mattakvshi.hibernateTrainProject.company.employee.Employee;
import ru.mattakvshi.hibernateTrainProject.company.employee.ITRole;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface EmployeeDAO {

    @Transactional
    Employee<ITRole> getEmployerByIndex(int index);

    List<Employee> getEmployerByRole(ITRole role, int company_id);

    List<Employee> getEmployerByAge(int age, int company_id);

}

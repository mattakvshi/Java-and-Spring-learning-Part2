package ru.mattakvshi.AOPTrainProject.dao;

import ru.mattakvshi.AOPTrainProject.company.employee.Employee;
import ru.mattakvshi.AOPTrainProject.company.employee.ITRole;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface EmployeeDAO {

    @Transactional
    Employee<ITRole> getEmployerByIndex(long index);

    List<Employee> getEmployerByRole(ITRole role, long company_id);

    List<Employee> getEmployerByAge(int age, long company_id);

}

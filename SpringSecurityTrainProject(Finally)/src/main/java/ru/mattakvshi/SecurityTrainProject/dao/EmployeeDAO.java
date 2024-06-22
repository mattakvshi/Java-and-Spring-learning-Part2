package ru.mattakvshi.SecurityTrainProject.dao;

import ru.mattakvshi.SecurityTrainProject.entity.employee.Employee;
import ru.mattakvshi.SecurityTrainProject.entity.employee.ITRole;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface EmployeeDAO {

    @Transactional
    Employee<ITRole> getEmployerByIndex(long index);

    List<Employee> getEmployerByRole(ITRole role, long company_id);

    List<Employee> getEmployerByAge(int age, long company_id);

}

package ru.mattakvshi.repositoryTrainProject.service;

import ru.mattakvshi.repositoryTrainProject.company.ITCompany;
import ru.mattakvshi.repositoryTrainProject.company.employee.Developer;
import ru.mattakvshi.repositoryTrainProject.company.employee.Employee;
import ru.mattakvshi.repositoryTrainProject.company.employee.ITRole;
import ru.mattakvshi.repositoryTrainProject.company.employee.PM;

import javax.transaction.Transactional;
import java.util.List;

public interface CompanyService {
    @Transactional
    Long createCompany(ITCompany company);

    ITCompany getCompany(long id);

    @Transactional
    void addDeveloper(Developer developer, long company_id);

    @Transactional
    void addPM(PM pm, long company_id);

    Employee<ITRole> getEmployerByIndex(long index);

    List<Employee> getEmployerByRole(ITRole role, long company_id);

    List<Employee> getEmployerByAge(int age, long company_id);
}

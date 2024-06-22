package ru.mattakvshi.SecurityTrainProject.service;

import ru.mattakvshi.SecurityTrainProject.entity.company.ITCompany;
import ru.mattakvshi.SecurityTrainProject.entity.employee.Developer;
import ru.mattakvshi.SecurityTrainProject.entity.employee.Employee;
import ru.mattakvshi.SecurityTrainProject.entity.employee.ITRole;
import ru.mattakvshi.SecurityTrainProject.entity.employee.PM;

import javax.transaction.Transactional;
import java.util.List;

public interface CompanyService {
    @Transactional
    Long createCompany(ITCompany company);

    ITCompany getCompany(long id);

    List<ITCompany> getMyCompanies();

    @Transactional
    void addDeveloper(Developer developer, long company_id);

    @Transactional
    void addPM(PM pm, long company_id);

    Employee<ITRole> getEmployerByIndex(long index);

    List<Employee> getEmployerByRole(ITRole role, long company_id);

    List<Employee> getEmployerByAge(int age, long company_id);
}

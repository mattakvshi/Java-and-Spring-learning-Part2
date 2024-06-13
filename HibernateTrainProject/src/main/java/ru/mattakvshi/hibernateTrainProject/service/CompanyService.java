package ru.mattakvshi.hibernateTrainProject.service;

import ru.mattakvshi.hibernateTrainProject.company.ITCompany;
import ru.mattakvshi.hibernateTrainProject.company.employee.Developer;
import ru.mattakvshi.hibernateTrainProject.company.employee.Employee;
import ru.mattakvshi.hibernateTrainProject.company.employee.ITRole;
import ru.mattakvshi.hibernateTrainProject.company.employee.PM;

import javax.transaction.Transactional;
import java.util.List;

public interface CompanyService {
    @Transactional
    Integer createCompany(ITCompany company);

    ITCompany getCompany(int id);

    @Transactional
    void addDeveloper(Developer developer, int company_id);

    @Transactional
    void addPM(PM pm, int company_id);

    Employee<ITRole> getEmployerByIndex(int index);

    List<Employee> getEmployerByRole(ITRole role, int company_id);

    List<Employee> getEmployerByAge(int age, int company_id);
}

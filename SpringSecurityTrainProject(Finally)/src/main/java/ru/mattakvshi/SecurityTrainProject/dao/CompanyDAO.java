package ru.mattakvshi.SecurityTrainProject.dao;

import ru.mattakvshi.SecurityTrainProject.entity.auth.Account;
import ru.mattakvshi.SecurityTrainProject.entity.company.ITCompany;
import ru.mattakvshi.SecurityTrainProject.entity.employee.Developer;
import ru.mattakvshi.SecurityTrainProject.entity.employee.Employee;
import ru.mattakvshi.SecurityTrainProject.entity.employee.ITRole;
import ru.mattakvshi.SecurityTrainProject.entity.employee.PM;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CompanyDAO {

    Long create(ITCompany company);
    ITCompany find(long id);
    List<ITCompany> findByDirector(Employee<ITRole> director);
    @Transactional
    void addDeveloper(Developer developer);
    @Transactional
    void addPM(PM pm);


}

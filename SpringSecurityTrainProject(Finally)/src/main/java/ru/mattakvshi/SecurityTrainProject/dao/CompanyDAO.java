package ru.mattakvshi.SecurityTrainProject.dao;

import ru.mattakvshi.SecurityTrainProject.company.ITCompany;
import ru.mattakvshi.SecurityTrainProject.company.employee.Developer;
import ru.mattakvshi.SecurityTrainProject.company.employee.PM;

import org.springframework.transaction.annotation.Transactional;

public interface CompanyDAO {

    Long create(ITCompany company);
    ITCompany find(long id);
    @Transactional
    void addDeveloper(Developer developer);
    @Transactional
    void addPM(PM pm);


}

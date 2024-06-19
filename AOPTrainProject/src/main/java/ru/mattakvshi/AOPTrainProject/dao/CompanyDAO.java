package ru.mattakvshi.AOPTrainProject.dao;

import ru.mattakvshi.AOPTrainProject.company.ITCompany;
import ru.mattakvshi.AOPTrainProject.company.employee.Developer;
import ru.mattakvshi.AOPTrainProject.company.employee.PM;

import org.springframework.transaction.annotation.Transactional;

public interface CompanyDAO {

    Long create(ITCompany company);
    ITCompany find(long id);
    @Transactional
    void addDeveloper(Developer developer);
    @Transactional
    void addPM(PM pm);


}

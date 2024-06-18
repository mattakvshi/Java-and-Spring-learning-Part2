package ru.mattakvshi.repositoryTrainProject.dao;

import ru.mattakvshi.repositoryTrainProject.company.ITCompany;
import ru.mattakvshi.repositoryTrainProject.company.employee.Developer;
import ru.mattakvshi.repositoryTrainProject.company.employee.PM;

import org.springframework.transaction.annotation.Transactional;

public interface CompanyDAO {

    Long create(ITCompany company);
    ITCompany find(long id);
    @Transactional
    void addDeveloper(Developer developer);
    @Transactional
    void addPM(PM pm);


}

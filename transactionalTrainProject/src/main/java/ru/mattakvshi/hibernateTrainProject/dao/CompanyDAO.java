package ru.mattakvshi.hibernateTrainProject.dao;

import ru.mattakvshi.hibernateTrainProject.company.ITCompany;
import ru.mattakvshi.hibernateTrainProject.company.employee.Developer;
import ru.mattakvshi.hibernateTrainProject.company.employee.PM;

import org.springframework.transaction.annotation.Transactional;

public interface CompanyDAO {

    Integer create(ITCompany company);
    ITCompany find(int id);
    @Transactional
    void addDeveloper(Developer developer);
    @Transactional
    void addPM(PM pm);


}

package ru.mattakvshi.SecurityTrainProject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.mattakvshi.SecurityTrainProject.aspect.LogExecutionTime;
import ru.mattakvshi.SecurityTrainProject.entity.auth.Account;
import ru.mattakvshi.SecurityTrainProject.entity.company.ITCompany;
import ru.mattakvshi.SecurityTrainProject.entity.employee.Developer;
import ru.mattakvshi.SecurityTrainProject.entity.employee.Employee;
import ru.mattakvshi.SecurityTrainProject.entity.employee.ITRole;
import ru.mattakvshi.SecurityTrainProject.entity.employee.PM;
import ru.mattakvshi.SecurityTrainProject.dao.CompanyDAO;
import ru.mattakvshi.SecurityTrainProject.dao.EmployeeDAO;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private ITCompany company;

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional
    public Long createCompany(ITCompany company){
        return companyDAO.create(company);
    }

    @Override
    @LogExecutionTime
    public ITCompany getCompany(long id) {
       return companyDAO.find(id);
    }

    @Override
    public List<ITCompany> getMyCompanies() {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return companyDAO.findByDirector(account.getEmployee());
    }

    @Override
    @Transactional
    public void addDeveloper(Developer developer, long company_id) {
        developer.setCompany(getCompany(company_id));
        companyDAO.addDeveloper(developer);

    }

    @Override
    @Transactional
    public void addPM(PM pm, long company_id) {
        pm.setCompany(getCompany(company_id));
        companyDAO.addPM(pm);
    }

    @Override
    @Transactional
    public Employee<ITRole> getEmployerByIndex(long index) {
        return employeeDAO.getEmployerByIndex(index);
    }

    @Override
    public List<Employee> getEmployerByRole(ITRole role, long company_id) {
        return employeeDAO.getEmployerByRole(role, company_id);
    }

    @Override
    public List<Employee> getEmployerByAge(int age, long company_id) {
        return employeeDAO.getEmployerByAge(age, company_id);
    }
}

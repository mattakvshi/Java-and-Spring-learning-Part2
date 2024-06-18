package ru.mattakvshi.repositoryTrainProject.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.mattakvshi.repositoryTrainProject.company.ITCompany;
import ru.mattakvshi.repositoryTrainProject.company.employee.Developer;
import ru.mattakvshi.repositoryTrainProject.company.employee.Employee;
import ru.mattakvshi.repositoryTrainProject.company.employee.ITRole;
import ru.mattakvshi.repositoryTrainProject.company.employee.PM;
import ru.mattakvshi.repositoryTrainProject.dao.CompanyDAO;
import ru.mattakvshi.repositoryTrainProject.dao.EmployeeDAO;
import ru.mattakvshi.repositoryTrainProject.dao.repository.CompanyRepository;
import ru.mattakvshi.repositoryTrainProject.dao.repository.DeveloperRepository;
import ru.mattakvshi.repositoryTrainProject.dao.repository.EmployeeRepository;
import ru.mattakvshi.repositoryTrainProject.dao.repository.PMRepository;

import java.util.List;

@Slf4j
@Primary //Для того чтобы спринг брал именно это реализацию как основную
@Component
public class CompanyRepositoryImpl2 implements CompanyDAO, EmployeeDAO {

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private DeveloperRepository developerRepository;

  @Autowired
  private PMRepository pmRepository;

  @Autowired
  private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Long create(ITCompany company) {
        ITCompany savedCompany = companyRepository.save(company);
        return savedCompany.getId();
    }

    @Override
    public ITCompany find(long id) {
        log.info("I working with CrudRepository and CompanyRepositoryImpl2 because this Primary!!");
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void addDeveloper(Developer developer) {
        developerRepository.save(developer);
    }

    @Override
    @Transactional
    public void addPM(PM pm) {
        pmRepository.save(pm);
    }

    @Override
    @Transactional
    public Employee<ITRole> getEmployerByIndex(long index) {
        Employee<ITRole> employee =  employeeRepository.findById(index).orElse(null);
        return employee;
    }

    @Override
    public List<Employee> getEmployerByRole(ITRole role, long company_id) {
        log.info("I work, so I exist");
        ITCompany company = find(company_id);

        //return employeeRepository.findByRoleAndCompany(role, company);
        //return employeeRepository.findByRoleAndCompanyId(role, company_id);

        return employeeRepository.findByRoleAndCompanyQuery(role, company);
    }

    @Override
    public List<Employee> getEmployerByAge(int age, long company_id) {
        log.info("I work, so I exist to");
        ITCompany company = find(company_id);

        //return employeeRepository.findByAgeAndCompany(age, company);
        //return employeeRepository.findByAgeAndCompanyId(age, company_id);

        return employeeRepository.findByAgeAndCompanyQuery(age, company);
    }
}
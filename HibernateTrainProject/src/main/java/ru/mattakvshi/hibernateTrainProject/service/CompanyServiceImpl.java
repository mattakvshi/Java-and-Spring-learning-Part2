package ru.mattakvshi.hibernateTrainProject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mattakvshi.hibernateTrainProject.company.ITCompany;
import ru.mattakvshi.hibernateTrainProject.company.employee.Developer;
import ru.mattakvshi.hibernateTrainProject.company.employee.Employee;
import ru.mattakvshi.hibernateTrainProject.company.employee.ITRole;
import ru.mattakvshi.hibernateTrainProject.company.employee.PM;
import ru.mattakvshi.hibernateTrainProject.dao.CompanyRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
//    @Qualifier("RequestScopedCompany")
    private ITCompany company;


    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional
    public Integer createCompany(ITCompany company){
        return companyRepository.create(company);
    }

    @Override
    public ITCompany getCompany(int id) {
       return companyRepository.find(id);
    }

    @Override
    @Transactional
    public void addDeveloper(Developer developer, int company_id) {
        //company.getEntities().add(developer);
        developer.setCompany(getCompany(company_id));

        companyRepository.addDeveloper(developer);

    }

    @Override
    @Transactional
    public void addPM(PM pm, int company_id) {
        //company.getEmployees().add(pm);
        pm.setCompany(getCompany(company_id));

        companyRepository.addPM(pm);
    }

    @Override
    @Transactional
    public Employee<ITRole> getEmployerByIndex(int index) {
        //Employer<ITRole> employer = company.getEntities().get(index);

        return companyRepository.getEmployerByIndex(index);
    }

    @Override
    public List<Employee> getEmployerByRole(ITRole role, int company_id) {

//        List<Employee<ITRole>> employees = company.getEmployees().stream()
//                .filter(employer -> employer.getRole().equals(role))
//                .collect(Collectors.toList());

        return companyRepository.getEmployerByRole(role, company_id);
    }

    @Override
    public List<Employee> getEmployerByAge(int age, int company_id) {
        return companyRepository.getEmployerByAge(age, company_id);
    }
}

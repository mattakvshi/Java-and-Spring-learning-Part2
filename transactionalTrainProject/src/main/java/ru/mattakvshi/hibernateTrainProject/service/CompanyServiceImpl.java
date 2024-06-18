package ru.mattakvshi.hibernateTrainProject.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.mattakvshi.hibernateTrainProject.company.ITCompany;
import ru.mattakvshi.hibernateTrainProject.company.employee.Developer;
import ru.mattakvshi.hibernateTrainProject.company.employee.Employee;
import ru.mattakvshi.hibernateTrainProject.company.employee.ITRole;
import ru.mattakvshi.hibernateTrainProject.company.employee.PM;
import ru.mattakvshi.hibernateTrainProject.dao.CompanyRepository;

import java.util.List;

@Slf4j
@Service
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private ITCompany company;


    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional //Аннотация по умолчанию содержит для свойств следующие параметры
    // Isolation.DEFAULT - то есть будет использован тот уровень, что стоит в Базе Данных
    // Propagation.REQUIRED - то есть если есть транзакция уже, будет использщована онае, если нет, создастся новая.
    public Integer createCompany(ITCompany company){
        return companyRepository.create(company);
    }

    @Override
    public ITCompany getCompany(int id) {
       return companyRepository.find(id);
    }

    @Override
    //Самое жествое пессиместичное блокирование, при котором доступ к сущьности из других траназкций вообще полностью закрыт, пока не завершится текущая
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void addDeveloper(Developer developer, int company_id) {
        developer.setCompany(getCompany(company_id));

        companyRepository.addDeveloper(developer);

    }

    @Override
    //Почти всегда стоит по умолчанию в базах данных, позволяет читать из паралленых транзакций закоммиченные данные, пока основная транзакция работает с сущьностью.
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void addPM(PM pm, int company_id) {
        pm.setCompany(getCompany(company_id));

        companyRepository.addPM(pm);
    }

    @Override
    //По истечению 10 секунд если транзакция не завершила работу будет вызван ROLLBACK
    @Transactional(timeout = 10)
    public Employee<ITRole> getEmployerByIndex(int index) {
        return companyRepository.getEmployerByIndex(index);
    }

    @Override
    public List<Employee> getEmployerByRole(ITRole role, int company_id) {
        return companyRepository.getEmployerByRole(role, company_id);
    }

    @Override
    public List<Employee> getEmployerByAge(int age, int company_id) {
        return companyRepository.getEmployerByAge(age, company_id);
    }
}

package ru.mattakvshi.SecurityTrainProject.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mattakvshi.SecurityTrainProject.company.employee.PM;

@Repository
public interface PMRepository extends CrudRepository<PM, Long> {
    //Аналогично c DeveloperRepository
}

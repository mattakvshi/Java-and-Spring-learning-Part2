package ru.mattakvshi.repositoryTrainProject.dao.repository;

import lombok.extern.java.Log;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mattakvshi.repositoryTrainProject.company.employee.Developer;

@Repository
public interface DeveloperRepository extends CrudRepository<Developer, Long> {
    //Здесь как и для CompanyRepository не пришлось писать никакой реализации, так как в контроллере у нас есть только добавление нового разработчика,
    // а для этого нам хватает методжа save из CrudRepository.
}

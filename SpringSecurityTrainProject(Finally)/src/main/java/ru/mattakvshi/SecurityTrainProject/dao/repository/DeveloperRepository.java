package ru.mattakvshi.SecurityTrainProject.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mattakvshi.SecurityTrainProject.entity.employee.Developer;

@Repository
public interface DeveloperRepository extends CrudRepository<Developer, Long> {
    //Здесь как и для CompanyRepository не пришлось писать никакой реализации, так как в контроллере у нас есть только добавление нового разработчика,
    // а для этого нам хватает методжа save из CrudRepository.
}

package ru.mattakvshi.SecurityTrainProject.dao.repository.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.mattakvshi.SecurityTrainProject.entity.auth.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByLogin(String login);
}

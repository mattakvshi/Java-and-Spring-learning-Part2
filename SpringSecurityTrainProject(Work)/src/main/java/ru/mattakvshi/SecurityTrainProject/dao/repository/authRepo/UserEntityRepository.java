package ru.mattakvshi.SecurityTrainProject.dao.repository.authRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mattakvshi.SecurityTrainProject.authEntity.Account;

@Repository
public interface UserEntityRepository extends JpaRepository<Account, Long> {
    Account findByLogin(String login);
}

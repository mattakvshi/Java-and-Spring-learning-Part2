package ru.mattakvshi.SecurityTrainProject.dao.repository.authRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mattakvshi.SecurityTrainProject.authEntity.RoleEntity;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}

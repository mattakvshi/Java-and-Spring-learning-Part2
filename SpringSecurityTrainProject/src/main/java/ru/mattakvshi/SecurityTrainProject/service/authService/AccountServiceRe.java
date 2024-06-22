package ru.mattakvshi.SecurityTrainProject.service.authService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.mattakvshi.SecurityTrainProject.authEntity.Account;
import ru.mattakvshi.SecurityTrainProject.authEntity.RoleEntity;
import ru.mattakvshi.SecurityTrainProject.dao.repository.authRepo.RoleEntityRepository;
import ru.mattakvshi.SecurityTrainProject.dao.repository.authRepo.UserEntityRepository;

@Service
public class AccountServiceRe {

    @Autowired
    private UserEntityRepository userEntityRepository;

   @Autowired
    private RoleEntityRepository roleEntityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; //Позволяет шифровать пороли и записывать в базу в зашифрованном виде

    public Account saveUser(Account account) {
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_USER");
        account.setRoleEntity(userRole);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return userEntityRepository.save(account);
    }

    public Account findByLogin(String login) {
        return userEntityRepository.findByLogin(login);
    }

    public Account findByLoginAndPassword(String login, String password) {
        Account account = findByLogin(login);
        if (account != null) {
            if (passwordEncoder.matches(password, account.getPassword())) {
                return account;
            }
        }
        return null;
    }

}

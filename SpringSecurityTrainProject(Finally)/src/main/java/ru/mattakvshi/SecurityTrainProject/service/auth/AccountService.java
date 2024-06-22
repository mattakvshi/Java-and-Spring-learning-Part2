package ru.mattakvshi.SecurityTrainProject.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mattakvshi.SecurityTrainProject.dao.repository.auth.AccountRepository;
import ru.mattakvshi.SecurityTrainProject.entity.auth.Account;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; //Позволяет шифровать пороли и записывать в базу в зашифрованном виде

    public Account saveUser(Account account) {;
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public Account findByLogin(String login) {
        return accountRepository.findByLogin(login);
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

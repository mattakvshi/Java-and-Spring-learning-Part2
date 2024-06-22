package ru.mattakvshi.SecurityTrainProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.mattakvshi.SecurityTrainProject.entity.auth.Account;
import ru.mattakvshi.SecurityTrainProject.service.auth.AccountService;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    //Создаём для пользователя CustomUserDetails по логину (username)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByLogin(username);
        return account;
    }
}

package ru.mattakvshi.SecurityTrainProject.config.securityConfig.userDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.mattakvshi.SecurityTrainProject.authEntity.Account;
import ru.mattakvshi.SecurityTrainProject.service.authService.AccountServiceRe;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountServiceRe accountService;

    //Создаём для пользователя CustomUserDetails по логину (username)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByLogin(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(account);
    }
}

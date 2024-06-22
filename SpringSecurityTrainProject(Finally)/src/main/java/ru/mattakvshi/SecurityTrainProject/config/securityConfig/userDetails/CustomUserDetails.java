package ru.mattakvshi.SecurityTrainProject.config.securityConfig.userDetails;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.mattakvshi.SecurityTrainProject.authEntity.Account;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public static CustomUserDetails fromUserEntityToCustomUserDetails(Account account)  {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.login = account.getLogin();
        customUserDetails.password = account.getPassword();
        customUserDetails.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(account.getRoleEntity().getName()));
        return customUserDetails;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

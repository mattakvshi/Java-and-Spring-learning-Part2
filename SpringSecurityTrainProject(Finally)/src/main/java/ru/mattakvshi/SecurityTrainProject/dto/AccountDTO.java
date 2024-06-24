package ru.mattakvshi.SecurityTrainProject.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import ru.mattakvshi.SecurityTrainProject.entity.auth.Account;

import java.util.Collection;

@Data
public class AccountDTO {

    private Long id;
    private String login;
    private String password;
    private ITEmployeeDTO employee;
    private Collection<? extends GrantedAuthority> Authorities;
    //private CompanyDTO company;

    public static AccountDTO from(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setLogin(account.getLogin());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setEmployee(ITEmployeeDTO.from(account.getEmployee()));
        accountDTO.setAuthorities(account.getAuthorities());
        //accountDTO.setCompany(CompanyDTO.from(account.getEmployee().getCompany()));

        return accountDTO;
    }
}

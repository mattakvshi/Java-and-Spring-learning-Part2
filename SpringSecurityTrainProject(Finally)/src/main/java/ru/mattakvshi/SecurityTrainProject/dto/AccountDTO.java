package ru.mattakvshi.SecurityTrainProject.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.CollectionUtils;
import ru.mattakvshi.SecurityTrainProject.entity.auth.Account;
import ru.mattakvshi.SecurityTrainProject.entity.company.ITCompany;
import ru.mattakvshi.SecurityTrainProject.entity.employee.Employee;
import ru.mattakvshi.SecurityTrainProject.entity.employee.ITRole;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class AccountDTO {

    private Long id;
    private String login;
    private String password;
    private ITEmployeeDTO employee;
    private Collection<? extends GrantedAuthority> Authorities;

    public static AccountDTO from(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setLogin(account.getLogin());
        accountDTO.setPassword(account.getPassword());
        accountDTO.setEmployee(ITEmployeeDTO.from(account.getEmployee()));
        accountDTO.setAuthorities(account.getAuthorities());

        return accountDTO;
    }
}

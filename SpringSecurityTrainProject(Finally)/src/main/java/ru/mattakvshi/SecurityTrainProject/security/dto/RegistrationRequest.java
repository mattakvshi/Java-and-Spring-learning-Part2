package ru.mattakvshi.SecurityTrainProject.security.dto;

import lombok.Data;
import ru.mattakvshi.SecurityTrainProject.entity.auth.Account;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationRequest {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    public Account toAccount() {
        Account account = new Account();
        account.setPassword(this.password);
        account.setLogin(this.login);
        return account;
    }
}
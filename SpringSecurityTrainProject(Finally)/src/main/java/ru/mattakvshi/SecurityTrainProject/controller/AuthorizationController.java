package ru.mattakvshi.SecurityTrainProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mattakvshi.SecurityTrainProject.authEntity.Account;
import ru.mattakvshi.SecurityTrainProject.config.securityConfig.AuthRequests;
import ru.mattakvshi.SecurityTrainProject.config.securityConfig.AuthResponse;
import ru.mattakvshi.SecurityTrainProject.config.securityConfig.JWTProvider;
import ru.mattakvshi.SecurityTrainProject.config.securityConfig.RegistrationRequest;
import ru.mattakvshi.SecurityTrainProject.service.authService.AccountServiceRe;

import javax.validation.Valid;

@RestController
public class AuthorizationController {

    @Autowired
    private AccountServiceRe accountServiceRe;

    @Autowired
    private JWTProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        Account account = new Account();
        account.setPassword(registrationRequest.getPassword());
        account.setLogin(registrationRequest.getLogin());
        accountServiceRe.saveUser(account);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequests request){
        Account account = accountServiceRe.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(account.getLogin());
        return new AuthResponse(token);
    }

    @GetMapping("/user/get")
    public String getUser() {
        return "USER";
    }
}

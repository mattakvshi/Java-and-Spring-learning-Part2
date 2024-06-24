package ru.mattakvshi.SecurityTrainProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.mattakvshi.SecurityTrainProject.dto.AccountDTO;
import ru.mattakvshi.SecurityTrainProject.entity.auth.Account;
import ru.mattakvshi.SecurityTrainProject.security.dto.AuthRequests;
import ru.mattakvshi.SecurityTrainProject.security.dto.AuthResponse;
import ru.mattakvshi.SecurityTrainProject.security.JWTProvider;
import ru.mattakvshi.SecurityTrainProject.security.dto.RegistrationRequest;
import ru.mattakvshi.SecurityTrainProject.service.CompanyService;
import ru.mattakvshi.SecurityTrainProject.service.auth.AccountService;

import javax.validation.Valid;

@RestController
public class AuthorizationController {

    @Autowired
    private AccountService accountServiceRe;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private JWTProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        accountServiceRe.saveUser(registrationRequest.toAccount());
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequests request){
        Account account = accountServiceRe.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(account.getLogin());
        return new AuthResponse(token);
    }

    @GetMapping("/me")
    public ResponseEntity<AccountDTO> getCurrentUser() {
        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        AccountDTO accountDTO = AccountDTO.from(account);
        return ResponseEntity.ok(accountDTO);
    }
}

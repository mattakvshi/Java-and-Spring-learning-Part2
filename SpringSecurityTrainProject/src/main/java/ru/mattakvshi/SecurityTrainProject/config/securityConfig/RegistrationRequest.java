package ru.mattakvshi.SecurityTrainProject.config.securityConfig;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String login;

    private String password;
}
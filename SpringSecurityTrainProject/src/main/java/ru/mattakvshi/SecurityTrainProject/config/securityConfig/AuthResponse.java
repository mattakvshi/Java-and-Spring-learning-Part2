package ru.mattakvshi.SecurityTrainProject.config.securityConfig;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    private String jwtToken;
}

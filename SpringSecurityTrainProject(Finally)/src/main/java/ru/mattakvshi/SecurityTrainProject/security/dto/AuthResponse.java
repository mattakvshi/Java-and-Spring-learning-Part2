package ru.mattakvshi.SecurityTrainProject.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    private String jwtToken;
}

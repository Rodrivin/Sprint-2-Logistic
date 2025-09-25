package com.example.logisticapi.services;

import com.example.logisticapi.dtos.LoginRequest;
import com.example.logisticapi.dtos.LoginResponse;
import com.example.logisticapi.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse authenticate(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            String token = jwtUtil.generateToken(authentication.getName());

            return new LoginResponse(token, "Autenticação realizada com sucesso!");
        } catch (AuthenticationException e) {
            return new LoginResponse(null, "Credenciais inválidas");
        }
    }
}

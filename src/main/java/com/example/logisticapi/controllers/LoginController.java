package com.example.logisticapi.controllers;

import com.example.logisticapi.dtos.LoginRequest;
import com.example.logisticapi.dtos.LoginResponse;
import com.example.logisticapi.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // POST /api/login - Endpoint para autenticação de usuário.
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = loginService.authenticate(loginRequest);
        if (response.getToken() != null) {
            return ResponseEntity.ok(response);
        } else {
            // Retorna 401 Unauthorized se as credenciais forem inválidas.
            return ResponseEntity.status(401).body(response);
        }
    }
}
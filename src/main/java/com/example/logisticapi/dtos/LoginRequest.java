package com.example.logisticapi.dtos;

// Data Transfer Object (DTO) para a requisição de login.
// Contém os campos necessários para um usuário tentar fazer login.
public class LoginRequest {
    private String email;
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
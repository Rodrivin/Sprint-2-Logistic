// Conteúdo do ficheiro: src/main/java/com/example/logisticapi/dtos/LoginResponse.java
package com.example.logisticapi.dtos;

// Data Transfer Object (DTO) para a resposta de login.
// Retorna informações sobre o resultado do login (sucesso/falha e um token simulado).
public class LoginResponse {
    private String message;
    private String token; // Em um sistema real, seria um JWT ou similar.
    private Long userId; // ID do usuário logado
    private String nome; // <--- NOVO CAMPO: Nome do usuário

    public LoginResponse(String message, String token, Long userId, String nome) { // <--- Construtor atualizado
        this.message = message;
        this.token = token;
        this.userId = userId;
        this.nome = nome; // Inicializa o novo campo
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNome() { // <--- NOVO GETTER
        return nome;
    }

    public void setNome(String nome) { // <--- NOVO SETTER
        this.nome = nome;
    }
}
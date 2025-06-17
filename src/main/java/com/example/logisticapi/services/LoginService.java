// Conteúdo do ficheiro: src/main/java/com/example/logisticapi/services/LoginService.java
package com.example.logisticapi.services;

import com.example.logisticapi.dtos.LoginRequest;
import com.example.logisticapi.dtos.LoginResponse;
import com.example.logisticapi.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

// Este serviço lida com a lógica de autenticação.
// Para esta sprint, faremos uma validação simples de credenciais.
@Service
public class LoginService {

    private final UsuarioService usuarioService;

    public LoginService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public LoginResponse authenticate(LoginRequest loginRequest) {
        // Busca o usuário pelo email
        Optional<Usuario> usuarioOptional = usuarioService.findByEmail(loginRequest.getEmail());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            // Verifica se a senha corresponde (em um sistema real, usaria criptografia como BCrypt)
            if (usuario.getSenha().equals(loginRequest.getSenha())) {
                // Autenticação bem-sucedida
                // Gerar um token simples para simulação (em produção seria um JWT)
                String simulatedToken = "fake-jwt-token-" + usuario.getId();
                // Retorna o nome do usuário na resposta
                return new LoginResponse("Login bem-sucedido!", simulatedToken, usuario.getId(), usuario.getNome()); // <--- Nome adicionado aqui
            }
        }
        // Credenciais inválidas
        return new LoginResponse("Email ou senha inválidos.", null, null, null); // <--- Nome nulo para falha
    }
}
package com.example.logisticapi.dtos;

public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String tipoUsuario;

    public UsuarioResponseDTO(Long id, String nome, String email, String tipoUsuario) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters
}

package com.example.logisticapi.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String senha; // Em um projeto real, esta senha deveria ser criptografada.

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 50)
    private String tipoUsuario; // Ex: "ADMIN", "MOTORISTA", "OPERADOR"

    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    public Usuario() {
    }

    public Usuario(String email, String senha, String nome, String tipoUsuario) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
        // Não defina dataCadastro aqui se você for usar @PrePersist para o construtor vazio.
        // Ou, se for usar este construtor, certifique-se de que ele seja chamado sempre.
        // A abordagem com @PrePersist é mais robusta para entrada via JSON.
    }

    // Método que será chamado automaticamente pelo JPA/Hibernate antes de persistir uma nova entidade.
    @PrePersist
    public void prePersist() {
        // Define a data de cadastro apenas se ela ainda não foi definida.
        // Isso cobre casos onde o objeto é criado via construtor vazio e setters.
        if (this.dataCadastro == null) {
            this.dataCadastro = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
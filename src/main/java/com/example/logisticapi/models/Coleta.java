package com.example.logisticapi.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "coletas")
public class Coleta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String descricao;

    @Column(nullable = false, length = 100)
    private String enderecoColeta;

    @Column(nullable = false)
    private LocalDateTime dataColetaAgendada;

    @Column(nullable = true)
    private LocalDateTime dataColetaRealizada;

    @ManyToOne
    @JoinColumn(name = "ordem_id", nullable = false)
    private Ordem ordem; // A coleta está associada a uma ordem específica

    @Column(nullable = false, length = 50)
    private String status; // Ex: "AGENDADA", "EM_ANDAMENTO", "CONCLUIDA", "CANCELADA"

    public Coleta() {
    }

    public Coleta(String descricao, String enderecoColeta, LocalDateTime dataColetaAgendada, Ordem ordem, String status) {
        this.descricao = descricao;
        this.enderecoColeta = enderecoColeta;
        this.dataColetaAgendada = dataColetaAgendada;
        this.ordem = ordem;
        this.status = status;
    }

    // Método que será chamado automaticamente pelo JPA/Hibernate antes de persistir uma nova entidade.
    @PrePersist
    public void prePersist() {
        // Define a data de coleta agendada se ela ainda não foi definida.
        if (this.dataColetaAgendada == null) {
            this.dataColetaAgendada = LocalDateTime.now();
        }
        // Se a data de coleta realizada não foi definida, assuma nulo inicialmente.
        if (this.dataColetaRealizada == null) {
            this.dataColetaRealizada = null; // Garante que não há valor ao criar
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEnderecoColeta() {
        return enderecoColeta;
    }

    public void setEnderecoColeta(String enderecoColeta) {
        this.enderecoColeta = enderecoColeta;
    }

    public LocalDateTime getDataColetaAgendada() {
        return dataColetaAgendada;
    }

    public void setDataColetaAgendada(LocalDateTime dataColetaAgendada) {
        this.dataColetaAgendada = dataColetaAgendada;
    }

    public LocalDateTime getDataColetaRealizada() {
        return dataColetaRealizada;
    }

    public void setDataColetaRealizada(LocalDateTime dataColetaRealizada) {
        this.dataColetaRealizada = dataColetaRealizada;
    }

    public Ordem getOrdem() {
        return ordem;
    }

    public void setOrdem(Ordem ordem) {
        this.ordem = ordem;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
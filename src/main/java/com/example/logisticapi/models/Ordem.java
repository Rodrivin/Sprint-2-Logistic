
package com.example.logisticapi.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ordens")
public class Ordem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String descricao;

    @Column(nullable = false, length = 100)
    private String enderecoOrigem;

    @Column(nullable = false, length = 100)
    private String enderecoDestino;

    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    // Relacionamento com Veiculo: Muitas ordens podem ser atribuídas a um Veiculo
    // @ManyToOne indica um relacionamento de muitos para um.
    // @JoinColumn especifica a coluna de chave estrangeira na tabela de ordens que referencia veiculos.
    // nullable = true, pois uma ordem pode não ter um veículo atribuído inicialmente.
    @ManyToOne
    @JoinColumn(name = "veiculo_id", nullable = true)
    private Veiculo veiculo;

    // Relacionamento com Usuario (motorista): Muitas ordens podem ser atribuídas a um Usuario (motorista)
    @ManyToOne
    @JoinColumn(name = "motorista_id", nullable = true)
    private Usuario motorista; // O usuário que será o motorista para esta ordem

    @Column(nullable = false, length = 50)
    private String status; // Ex: "PENDENTE", "EM_ANDAMENTO", "CONCLUIDA", "CANCELADA"

    public Ordem() {
    }

    public Ordem(String descricao, String enderecoOrigem, String enderecoDestino, Veiculo veiculo, Usuario motorista, String status) {
        this.descricao = descricao;
        this.enderecoOrigem = enderecoOrigem;
        this.enderecoDestino = enderecoDestino;
        this.dataCriacao = LocalDateTime.now();
        this.veiculo = veiculo;
        this.motorista = motorista;
        this.status = status;
    }

    // Método que será chamado automaticamente pelo JPA/Hibernate antes de persistir uma nova entidade.
    @PrePersist
    public void prePersist() {
        if (this.dataCriacao == null) {
            this.dataCriacao = LocalDateTime.now();
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

    public String getEnderecoOrigem() {
        return enderecoOrigem;
    }

    public void setEnderecoOrigem(String enderecoOrigem) {
        this.enderecoOrigem = enderecoOrigem;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public void setEnderecoDestino(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Usuario getMotorista() {
        return motorista;
    }

    public void setMotorista(Usuario motorista) {
        this.motorista = motorista;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
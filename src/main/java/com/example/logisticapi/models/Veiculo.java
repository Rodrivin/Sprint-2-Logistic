package com.example.logisticapi.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

// @Entity indica que esta classe é uma entidade JPA e será mapeada para uma tabela no banco de dados.
@Entity
// @Table especifica o nome da tabela no banco de dados.
@Table(name = "veiculos")
public class Veiculo implements Serializable {
    // Um ID de versão para controle de serialização.
    private static final long serialVersionUID = 1L;

    // @Id indica que este campo é a chave primária da entidade.
    @Id
    // @GeneratedValue configura como o valor da chave primária é gerado.
    // GenerationType.IDENTITY usa a identidade do banco de dados (auto-incremento).
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column mapeia o campo para uma coluna no banco de dados.
    // nullable = false indica que a coluna não pode ser nula.
    // unique = true indica que os valores nesta coluna devem ser únicos.
    @Column(nullable = false, unique = true, length = 20)
    private String placa;

    @Column(nullable = false, length = 100)
    private String modelo;

    @Column(nullable = false)
    private Integer anoFabricacao;

    @Column(nullable = false)
    private Integer capacidadeCargaKg;

    // A data de cadastro não pode ser nula e será definida automaticamente antes da persistência.
    @Column(nullable = false)
    private LocalDateTime dataCadastro;

    // Construtor padrão (necessário para JPA).
    public Veiculo() {
    }

    // Construtor com parâmetros.
    public Veiculo(String placa, String modelo, Integer anoFabricacao, Integer capacidadeCargaKg) {
        this.placa = placa;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.capacidadeCargaKg = capacidadeCargaKg;
        // Removido this.dataCadastro = LocalDateTime.now(); para usar @PrePersist
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

    // Métodos Getters e Setters para acessar e modificar os atributos.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Integer getCapacidadeCargaKg() {
        return capacidadeCargaKg;
    }

    public void setCapacidadeCargaKg(Integer capacidadeCargaKg) {
        this.capacidadeCargaKg = capacidadeCargaKg;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
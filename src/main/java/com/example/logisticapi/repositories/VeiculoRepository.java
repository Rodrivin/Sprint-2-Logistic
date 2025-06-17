package com.example.logisticapi.repositories;

import com.example.logisticapi.models.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// @Repository indica que esta interface é um componente de acesso a dados.
// Ela estende JpaRepository, que fornece métodos CRUD (Create, Read, Update, Delete) prontos para uso.
// Veiculo é o tipo da entidade e Long é o tipo da chave primária da entidade.
@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    // Método personalizado para encontrar um veículo pela placa.
    // Spring Data JPA é inteligente o suficiente para gerar a query automaticamente com base no nome do método.
    Optional<Veiculo> findByPlaca(String placa);
}
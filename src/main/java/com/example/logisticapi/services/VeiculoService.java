package com.example.logisticapi.services;

import com.example.logisticapi.models.Veiculo;
import com.example.logisticapi.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// @Service indica que esta classe é um componente de serviço e contém a lógica de negócios.
@Service
public class VeiculoService {

    // @Autowired injeta uma instância de VeiculoRepository.
    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    // Retorna todos os veículos.
    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    // Retorna um veículo pelo ID.
    public Optional<Veiculo> findById(Long id) {
        return veiculoRepository.findById(id);
    }

    // Adicionado: Retorna um veículo pela placa.
    public Optional<Veiculo> findByPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa);
    }

    // Salva um novo veículo ou atualiza um existente.
    public Veiculo save(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    // Deleta um veículo pelo ID.
    public void deleteById(Long id) {
        veiculoRepository.deleteById(id);
    }
}
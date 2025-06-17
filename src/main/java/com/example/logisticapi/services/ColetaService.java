package com.example.logisticapi.services;

import com.example.logisticapi.models.Coleta;
import com.example.logisticapi.repositories.ColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ColetaService {

    private final ColetaRepository coletaRepository;

    public ColetaService(ColetaRepository coletaRepository) {
        this.coletaRepository = coletaRepository;
    }

    public List<Coleta> findAll() {
        return coletaRepository.findAll();
    }

    public Optional<Coleta> findById(Long id) {
        return coletaRepository.findById(id);
    }

    public Coleta save(Coleta coleta) {
        return coletaRepository.save(coleta);
    }

    public void deleteById(Long id) {
        coletaRepository.deleteById(id);
    }
}
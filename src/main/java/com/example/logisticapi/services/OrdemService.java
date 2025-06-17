package com.example.logisticapi.services;

import com.example.logisticapi.models.Ordem;
import com.example.logisticapi.repositories.OrdemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdemService {

    private final OrdemRepository ordemRepository;

    public OrdemService(OrdemRepository ordemRepository) {
        this.ordemRepository = ordemRepository;
    }

    public List<Ordem> findAll() {
        return ordemRepository.findAll();
    }

    public Optional<Ordem> findById(Long id) {
        return ordemRepository.findById(id);
    }

    public Ordem save(Ordem ordem) {
        return ordemRepository.save(ordem);
    }

    public void deleteById(Long id) {
        ordemRepository.deleteById(id);
    }
}
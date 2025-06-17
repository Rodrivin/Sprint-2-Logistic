package com.example.logisticapi.repositories;

import com.example.logisticapi.models.Coleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColetaRepository extends JpaRepository<Coleta, Long> {
}
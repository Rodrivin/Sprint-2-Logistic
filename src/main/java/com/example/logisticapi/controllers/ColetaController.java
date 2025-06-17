package com.example.logisticapi.controllers;

import com.example.logisticapi.models.Coleta;
import com.example.logisticapi.models.Ordem;
import com.example.logisticapi.services.ColetaService;
import com.example.logisticapi.services.OrdemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coletas")
public class ColetaController {

    private final ColetaService coletaService;
    private final OrdemService ordemService; // Para buscar a Ordem associada

    public ColetaController(ColetaService coletaService, OrdemService ordemService) {
        this.coletaService = coletaService;
        this.ordemService = ordemService;
    }

    @GetMapping
    public ResponseEntity<List<Coleta>> getAllColetas() {
        List<Coleta> coletas = coletaService.findAll();
        return ResponseEntity.ok(coletas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coleta> getColetaById(@PathVariable Long id) {
        Optional<Coleta> coleta = coletaService.findById(id);
        return coleta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Coleta> createColeta(@RequestBody Coleta coleta) {
        // Assegura que a ordem associada existe antes de salvar a coleta
        if (coleta.getOrdem() == null || coleta.getOrdem().getId() == null) {
            return ResponseEntity.badRequest().body(null); // Ordem é obrigatória
        }
        Optional<Ordem> ordemOptional = ordemService.findById(coleta.getOrdem().getId());
        if (ordemOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Ordem não encontrada
        }
        coleta.setOrdem(ordemOptional.get()); // Garante que a ordem completa seja associada

        Coleta savedColeta = coletaService.save(coleta);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedColeta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coleta> updateColeta(@PathVariable Long id, @RequestBody Coleta coletaDetails) {
        Optional<Coleta> coletaOptional = coletaService.findById(id);
        if (coletaOptional.isPresent()) {
            Coleta coleta = coletaOptional.get();
            coleta.setDescricao(coletaDetails.getDescricao());
            coleta.setEnderecoColeta(coletaDetails.getEnderecoColeta());
            coleta.setDataColetaAgendada(coletaDetails.getDataColetaAgendada());
            coleta.setDataColetaRealizada(coletaDetails.getDataColetaRealizada());
            coleta.setStatus(coletaDetails.getStatus());

            // Atualiza a Ordem associada se um novo ID for fornecido
            if (coletaDetails.getOrdem() != null && coletaDetails.getOrdem().getId() != null) {
                Optional<Ordem> ordemOptional = ordemService.findById(coletaDetails.getOrdem().getId());
                if (ordemOptional.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Ordem não encontrada
                }
                coleta.setOrdem(ordemOptional.get());
            } else {
                return ResponseEntity.badRequest().body(null); // Ordem não pode ser nula
            }

            Coleta updatedColeta = coletaService.save(coleta);
            return ResponseEntity.ok(updatedColeta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteColeta(@PathVariable Long id) {
        if (coletaService.findById(id).isPresent()) {
            coletaService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
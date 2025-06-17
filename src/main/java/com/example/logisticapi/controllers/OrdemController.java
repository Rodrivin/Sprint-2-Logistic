package com.example.logisticapi.controllers;

import com.example.logisticapi.models.Ordem;
import com.example.logisticapi.services.OrdemService;
import com.example.logisticapi.services.VeiculoService;
import com.example.logisticapi.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ordens")
public class OrdemController {

    private final OrdemService ordemService;
    private final VeiculoService veiculoService;
    private final UsuarioService usuarioService;

    public OrdemController(OrdemService ordemService, VeiculoService veiculoService, UsuarioService usuarioService) {
        this.ordemService = ordemService;
        this.veiculoService = veiculoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<Ordem>> getAllOrdens() {
        List<Ordem> ordens = ordemService.findAll();
        return ResponseEntity.ok(ordens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ordem> getOrdemById(@PathVariable Long id) {
        Optional<Ordem> ordem = ordemService.findById(id);
        return ordem.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ordem> createOrdem(@RequestBody Ordem ordem) {
        // Validações para Veiculo e Motorista, se IDs forem fornecidos.
        if (ordem.getVeiculo() != null && ordem.getVeiculo().getId() != null) {
            veiculoService.findById(ordem.getVeiculo().getId())
                    .ifPresent(ordem::setVeiculo);
        }
        if (ordem.getMotorista() != null && ordem.getMotorista().getId() != null) {
            usuarioService.findById(ordem.getMotorista().getId())
                    .ifPresent(ordem::setMotorista);
        }

        Ordem savedOrdem = ordemService.save(ordem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrdem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ordem> updateOrdem(@PathVariable Long id, @RequestBody Ordem ordemDetails) {
        Optional<Ordem> ordemOptional = ordemService.findById(id);
        if (ordemOptional.isPresent()) {
            Ordem ordem = ordemOptional.get();
            ordem.setDescricao(ordemDetails.getDescricao());
            ordem.setEnderecoOrigem(ordemDetails.getEnderecoOrigem());
            ordem.setEnderecoDestino(ordemDetails.getEnderecoDestino());
            ordem.setStatus(ordemDetails.getStatus());

            // Atualiza Veiculo e Motorista se novos IDs forem fornecidos
            if (ordemDetails.getVeiculo() != null && ordemDetails.getVeiculo().getId() != null) {
                veiculoService.findById(ordemDetails.getVeiculo().getId())
                        .ifPresent(ordem::setVeiculo);
            } else if (ordemDetails.getVeiculo() == null) {
                ordem.setVeiculo(null); // Permite desvincular o veículo
            }

            if (ordemDetails.getMotorista() != null && ordemDetails.getMotorista().getId() != null) {
                usuarioService.findById(ordemDetails.getMotorista().getId())
                        .ifPresent(ordem::setMotorista);
            } else if (ordemDetails.getMotorista() == null) {
                ordem.setMotorista(null); // Permite desvincular o motorista
            }

            Ordem updatedOrdem = ordemService.save(ordem);
            return ResponseEntity.ok(updatedOrdem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrdem(@PathVariable Long id) {
        if (ordemService.findById(id).isPresent()) {
            ordemService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
package com.example.logisticapi.controllers;

import com.example.logisticapi.models.Veiculo;
import com.example.logisticapi.services.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// @RestController indica que esta classe é um controlador REST e os métodos retornarão dados JSON/XML.
@RestController
// @RequestMapping define o caminho base para todos os endpoints neste controller.
@RequestMapping("/api/veiculos")
public class VeiculoController {

    // @Autowired injeta a dependência do VeiculoService.
    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    // GET /api/veiculos - Retorna todos os veículos.
    @GetMapping
    public ResponseEntity<List<Veiculo>> getAllVeiculos() {
        List<Veiculo> veiculos = veiculoService.findAll();
        return ResponseEntity.ok(veiculos);
    }

    // GET /api/veiculos/{id} - Retorna um veículo pelo ID.
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> getVeiculoById(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoService.findById(id);
        // Retorna 200 OK com o veículo se encontrado, ou 404 Not Found se não.
        return veiculo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /api/veiculos - Cria um novo veículo.
    // @RequestBody mapeia o corpo da requisição JSON para o objeto Veiculo.
    @PostMapping
    public ResponseEntity<Veiculo> createVeiculo(@RequestBody Veiculo veiculo) {
        // Validação básica: verificar se a placa já existe (exemplo)
        if (veiculoService.findByPlaca(veiculo.getPlaca()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 Conflict
        }
        Veiculo savedVeiculo = veiculoService.save(veiculo);
        // Retorna 201 Created e o veículo criado.
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVeiculo);
    }

    // PUT /api/veiculos/{id} - Atualiza um veículo existente.
    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> updateVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculoDetails) {
        Optional<Veiculo> veiculoOptional = veiculoService.findById(id);
        if (veiculoOptional.isPresent()) {
            Veiculo veiculo = veiculoOptional.get();
            veiculo.setPlaca(veiculoDetails.getPlaca());
            veiculo.setModelo(veiculoDetails.getModelo());
            veiculo.setAnoFabricacao(veiculoDetails.getAnoFabricacao());
            veiculo.setCapacidadeCargaKg(veiculoDetails.getCapacidadeCargaKg());
            // A data de cadastro geralmente não é atualizada, mas depende da regra de negócio.
            // veiculo.setDataCadastro(veiculoDetails.getDataCadastro());
            Veiculo updatedVeiculo = veiculoService.save(veiculo);
            return ResponseEntity.ok(updatedVeiculo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/veiculos/{id} - Deleta um veículo.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id) {
        if (veiculoService.findById(id).isPresent()) {
            veiculoService.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
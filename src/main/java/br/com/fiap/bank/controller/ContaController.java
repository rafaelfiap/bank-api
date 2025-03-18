package br.com.fiap.bank.controller;

import br.com.fiap.bank.model.Conta;
import br.com.fiap.bank.service.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarConta(@RequestBody Conta conta) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(contaService.cadastrarConta(conta));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Conta>> listarContas() {
        return ResponseEntity.ok(contaService.listarContas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contaService.buscarPorId(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<?> buscarPorCpf(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok(contaService.buscarPorCpf(cpf));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/encerrar")
    public ResponseEntity<?> encerrarConta(@RequestBody Map<String, Long> requestBody) {
        try {
            Long id = requestBody.get("id");
            return ResponseEntity.ok(contaService.encerrarConta(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/deposito")
    public ResponseEntity<?> depositar(@RequestBody Map<String, Object> requestBody) {
        try {
            Long id = ((Number) requestBody.get("id")).longValue();
            Double valor = ((Number) requestBody.get("valor")).doubleValue();
            return ResponseEntity.ok(contaService.depositar(id, valor));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/saque")
    public ResponseEntity<?> sacar(@RequestBody Map<String, Object> requestBody) {
        try {
            Long id = ((Number) requestBody.get("id")).longValue();
            Double valor = ((Number) requestBody.get("valor")).doubleValue();
            return ResponseEntity.ok(contaService.sacar(id, valor));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/pix")
    public ResponseEntity<?> realizarPix(@RequestBody Map<String, Object> pixData) {
        try {
            if (!pixData.containsKey("origemId") || !pixData.containsKey("destinoId") || !pixData.containsKey("valor")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Campos obrigatórios: origemId, destinoId, valor");
            }

            Long origemId = ((Number) pixData.get("origemId")).longValue();
            Long destinoId = ((Number) pixData.get("destinoId")).longValue();
            Double valor = ((Number) pixData.get("valor")).doubleValue();

            return ResponseEntity.ok(contaService.realizarPix(origemId, destinoId, valor));
        } catch (ClassCastException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de tipo nos valores enviados: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao processar a requisição: " + e.getMessage());
        }
    }
}

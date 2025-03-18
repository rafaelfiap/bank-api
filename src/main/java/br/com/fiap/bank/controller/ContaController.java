package br.com.fiap.bank.controller;

import br.com.fiap.bank.model.Conta;
import br.com.fiap.bank.service.ContaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.Map;
import java.util.List;

/**
 * Controlador responsável pelo gerenciamento das contas bancárias.
 * 
 * @author Rafael e Lucas
 * @since 1.0
 * @version 1.1
 * @see Conta
 * @see ContaService
 */
@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    /**
     * Construtor do controlador de contas.
     * 
     * @param contaService Serviço responsável pelo gerenciamento das contas.
     */
    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    /**
     * Cadastra uma nova conta.
     * 
     * @param conta Dados da conta a ser cadastrada.
     * @return Conta criada ou erro caso haja problema.
     */
    @PostMapping
    public ResponseEntity<?> cadastrarConta(@RequestBody Conta conta) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(contaService.cadastrarConta(conta));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Lista todas as contas registradas.
     * 
     * @return Lista de contas.
     */
    @GetMapping
    public ResponseEntity<List<Conta>> listarContas() {
        return ResponseEntity.ok(contaService.listarContas());
    }

    /**
     * Busca uma conta pelo ID.
     * 
     * @param id Identificador da conta.
     * @return Conta encontrada ou erro caso não exista.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contaService.buscarPorId(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Busca uma conta pelo CPF do titular.
     * 
     * @param cpf CPF do titular.
     * @return Conta encontrada ou erro caso não exista.
     */
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<?> buscarPorCpf(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok(contaService.buscarPorCpf(cpf));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Encerra uma conta.
     * 
     * @param requestBody Contém o ID da conta a ser encerrada.
     * @return Conta encerrada ou erro caso não seja possível encerrar.
     */
    @PutMapping("/encerrar")
    public ResponseEntity<?> encerrarConta(@RequestBody Map<String, Long> requestBody) {
        try {
            Long id = requestBody.get("id");
            return ResponseEntity.ok(contaService.encerrarConta(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Realiza um depósito em uma conta.
     * 
     * @param requestBody Contém o ID da conta e o valor do depósito.
     * @return Conta atualizada ou erro caso não seja possível depositar.
     */
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

    /**
     * Realiza um saque de uma conta.
     * 
     * @param requestBody Contém o ID da conta e o valor do saque.
     * @return Conta atualizada ou erro caso não seja possível sacar.
     */
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

    /**
     * Realiza uma transferência PIX entre contas.
     * 
     * @param pixData Contém os IDs das contas de origem e destino e o valor do PIX.
     * @return Conta de origem atualizada ou erro caso a transferência não seja possível.
     */
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

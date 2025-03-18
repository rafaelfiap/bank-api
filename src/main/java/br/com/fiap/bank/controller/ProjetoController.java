package br.com.fiap.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável pelo retorno das informações do projeto.
 *
 * Este controlador fornece um endpoint que exibe o nome do projeto e os integrantes da equipe.
 * A rota base foi configurada para que o endpoint responda corretamente quando acessado via 
 * `http://localhost:8080/`.
 *
 * @author Rafael e Lucas
 * @since 1.0
 * @version 1.1
 */
@RestController
@RequestMapping("/") // Define que a rota base termina com "/"
public class ProjetoController {

    /**
     * Endpoint que retorna o nome do projeto e os integrantes da equipe.
     * 
     * Acessível via `http://localhost:8080/`, esta rota exibe informações básicas sobre o sistema.
     *
     * @return String contendo o nome do projeto e os integrantes da equipe.
     * @since 1.0
     */
    @GetMapping("/")
    public String getProjetoInfo() {
        return "Projeto: Bank | Equipe: Rafael e Lucas";
    }
}

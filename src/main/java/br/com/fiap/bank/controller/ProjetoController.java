package br.com.fiap.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsável pelo retorno das informações do projeto.
 * @since 1.0
 * @version 1.0
 */
@RestController
@RequestMapping("/")
public class ProjetoController {

    /**
     * Endpoint que retorna o nome do projeto e os integrantes da equipe.
     * @return String com informações do projeto.
     */
    @GetMapping
    public String getProjetoInfo() {
        return "Projeto: Bank | Equipe: Rafael e Lucas";
    }
}

package br.com.fiap.bank.repository;

import br.com.fiap.bank.model.Conta;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositório responsável pelo gerenciamento das contas bancárias em memória.
 * Simula um banco de dados utilizando uma lista interna.
 *
 * @author Rafael e Lucas
 * @since 1.0
 * @version 1.1
 */
@Repository
public class ContaRepository {

    private final List<Conta> contas = new ArrayList<>();
    private Long proximoId = 1L;

    /**
     * Salva uma conta na lista. Se a conta já existir, ela é substituída.
     *
     * @param conta A conta a ser salva ou atualizada.
     * @return A conta salva com seu ID atribuído.
     */
    public Conta salvar(Conta conta) {
        if (conta.getId() == null) {
            conta.setId(proximoId++);
        }
        contas.removeIf(c -> c.getId().equals(conta.getId())); // Remove caso já exista
        contas.add(conta);
        return conta;
    }

    /**
     * Lista todas as contas armazenadas.
     *
     * @return Uma lista contendo todas as contas.
     */
    public List<Conta> listarTodas() {
        return new ArrayList<>(contas);
    }

    /**
     * Busca uma conta pelo seu ID.
     *
     * @param id O identificador da conta.
     * @return Um Optional contendo a conta, se encontrada.
     */
    public Optional<Conta> buscarPorId(Long id) {
        return contas.stream()
                .filter(conta -> conta.getId().equals(id))
                .findFirst();
    }

    /**
     * Busca uma conta pelo CPF do titular.
     *
     * @param cpf O CPF do titular da conta.
     * @return Um Optional contendo a conta, se encontrada.
     */
    public Optional<Conta> buscarPorCpf(String cpf) {
        return contas.stream()
                .filter(conta -> conta.getCpfTitular().equals(cpf))
                .findFirst();
    }

}

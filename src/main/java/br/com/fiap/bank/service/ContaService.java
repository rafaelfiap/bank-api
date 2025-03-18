package br.com.fiap.bank.service;

import br.com.fiap.bank.model.Conta;
import br.com.fiap.bank.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Serviço responsável pelas operações bancárias, como cadastro, depósitos, saques e transferências via PIX.
 *
 * @author Rafael e Lucas
 * @since 1.0
 * @version 1.1
 * @see Conta
 * @see ContaRepository
 */
@Service
public class ContaService {

    private final ContaRepository contaRepository;

    /**
     * Construtor do serviço ContaService.
     *
     * @param contaRepository O repositório de contas a ser utilizado.
     */
    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    /**
     * Cadastra uma nova conta após validação.
     *
     * @param conta A conta a ser cadastrada.
     * @return A conta cadastrada.
     */
    public Conta cadastrarConta(Conta conta) {
        validarConta(conta);
        return contaRepository.salvar(conta);
    }

    /**
     * Lista todas as contas cadastradas.
     *
     * @return Lista de contas.
     */
    public List<Conta> listarContas() {
        return contaRepository.listarTodas();
    }

    /**
     * Busca uma conta pelo ID.
     *
     * @param id O identificador da conta.
     * @return A conta encontrada.
     * @throws IllegalArgumentException Se a conta não for encontrada.
     */
    public Conta buscarPorId(Long id) {
        return contaRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));
    }

    /**
     * Busca uma conta pelo CPF do titular.
     *
     * @param cpf O CPF do titular.
     * @return A conta encontrada.
     * @throws IllegalArgumentException Se a conta não for encontrada.
     */
    public Conta buscarPorCpf(String cpf) {
        return contaRepository.buscarPorCpf(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada para o CPF informado."));
    }

    /**
     * Encerra uma conta marcando-a como inativa.
     *
     * @param id O identificador da conta a ser encerrada.
     * @return A conta encerrada.
     */
    public Conta encerrarConta(Long id) {
        Conta conta = buscarPorId(id);
        conta.setAtiva(false);
        contaRepository.salvar(conta);
        return conta;
    }

    /**
     * Realiza um depósito em uma conta.
     *
     * @param id O identificador da conta.
     * @param valor O valor a ser depositado.
     * @return A conta atualizada.
     * @throws IllegalArgumentException Se a conta estiver inativa ou o valor for inválido.
     */
    public Conta depositar(Long id, Double valor) {
        Conta conta = buscarPorId(id);
        if (!conta.getAtiva()) {
            throw new IllegalArgumentException("Operação não permitida: Conta inativa.");
        }
        if (valor == null || valor <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser maior que zero.");
        }
        conta.setSaldo(conta.getSaldo() + valor);
        return contaRepository.salvar(conta);
    }

    /**
     * Realiza um saque em uma conta.
     *
     * @param id O identificador da conta.
     * @param valor O valor a ser sacado.
     * @return A conta atualizada.
     * @throws IllegalArgumentException Se a conta estiver inativa, o valor for inválido ou saldo insuficiente.
     */
    public Conta sacar(Long id, Double valor) {
        Conta conta = buscarPorId(id);
        if (!conta.getAtiva()) {
            throw new IllegalArgumentException("Operação não permitida: Conta inativa.");
        }
        if (valor == null || valor <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser maior que zero.");
        }
        if (conta.getSaldo() < valor) {
            throw new IllegalArgumentException("Saldo insuficiente para saque.");
        }
        conta.setSaldo(conta.getSaldo() - valor);
        return contaRepository.salvar(conta);
    }

    /**
     * Realiza uma transferência PIX entre contas.
     *
     * @param origemId O identificador da conta de origem.
     * @param destinoId O identificador da conta de destino.
     * @param valor O valor a ser transferido.
     * @return A conta de origem após a transferência.
     * @throws IllegalArgumentException Se as contas forem iguais, estiverem inativas ou saldo insuficiente.
     */
    public Conta realizarPix(Long origemId, Long destinoId, Double valor) {
        if (origemId.equals(destinoId)) {
            throw new IllegalArgumentException("Operação não permitida: A conta de origem e destino são as mesmas.");
        }
        Conta origem = buscarPorId(origemId);
        Conta destino = buscarPorId(destinoId);
        if (!origem.getAtiva()) {
            throw new IllegalArgumentException("Operação não permitida: Conta de origem inativa.");
        }
        if (!destino.getAtiva()) {
            throw new IllegalArgumentException("Operação não permitida: Conta de destino inativa.");
        }
        if (valor == null || valor <= 0) {
            throw new IllegalArgumentException("O valor da transferência deve ser maior que zero.");
        }
        if (origem.getSaldo() < valor) {
            throw new IllegalArgumentException("Saldo insuficiente para realizar a transferência.");
        }
        origem.setSaldo(origem.getSaldo() - valor);
        destino.setSaldo(destino.getSaldo() + valor);
        contaRepository.salvar(origem);
        contaRepository.salvar(destino);
        return origem;
    }

    /**
     * Valida os dados da conta antes do cadastro.
     *
     * @param conta A conta a ser validada.
     * @throws IllegalArgumentException Se algum dos campos obrigatórios estiver ausente ou inválido.
     */
    private void validarConta(Conta conta) {
        if (conta.getNomeTitular() == null || conta.getNomeTitular().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do titular é obrigatório.");
        }
        if (conta.getCpfTitular() == null || !conta.getCpfTitular().matches("\\d{11}")) {
            throw new IllegalArgumentException("O CPF deve ter exatamente 11 dígitos numéricos.");
        }
        if (conta.getDataAbertura() == null || conta.getDataAbertura().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de abertura não pode ser no futuro.");
        }
        if (conta.getSaldo() != null && conta.getSaldo() < 0) {
            throw new IllegalArgumentException("O saldo inicial não pode ser negativo.");
        }
        if (conta.getTipo() == null) {
            throw new IllegalArgumentException("O tipo da conta deve ser informado.");
        }
    }
}

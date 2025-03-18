package br.com.fiap.bank.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Classe que representa uma conta bancária.
 * Contém informações como número, agência, titular, saldo e tipo de conta.
 *
 * @author Rafael e Lucas
 * @since 1.0
 * @version 1.1
 * @see TipoConta
 */
public class Conta {
    private Long id;
    private String numero;
    private String agencia;
    private String nomeTitular;
    private String cpfTitular;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataAbertura;
    private Double saldo;
    private Boolean ativa;
    private TipoConta tipo;

    /**
     * Construtor padrão.
     */
    public Conta() {
    }

    /**
     * Construtor completo para inicializar uma conta com todos os atributos.
     *
     * @param id            O ID da conta.
     * @param numero        O número da conta.
     * @param agencia       A agência da conta.
     * @param nomeTitular   O nome do titular da conta.
     * @param cpfTitular    O CPF do titular da conta.
     * @param dataAbertura  A data de abertura da conta.
     * @param saldo        O saldo inicial da conta.
     * @param ativa        Indica se a conta está ativa.
     * @param tipo         O tipo da conta (Corrente, Poupança, Salário).
     */
    public Conta(Long id, String numero, String agencia, String nomeTitular, String cpfTitular, LocalDate dataAbertura, Double saldo, Boolean ativa, TipoConta tipo) {
        this.id = id;
        this.numero = numero;
        this.agencia = agencia;
        this.nomeTitular = nomeTitular;
        this.cpfTitular = cpfTitular;
        this.dataAbertura = dataAbertura;
        this.saldo = saldo;
        this.ativa = ativa;
        this.tipo = tipo;
    }

    /**
     * Obtém o ID da conta.
     * @return O ID da conta.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID da conta.
     * @param id O novo ID da conta.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o número da conta.
     * @return O número da conta.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Define o número da conta.
     * @param numero O novo número da conta.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Obtém a agência da conta.
     * @return A agência da conta.
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * Define a agência da conta.
     * @param agencia A nova agência da conta.
     */
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    /**
     * Obtém o nome do titular da conta.
     * @return O nome do titular da conta.
     */
    public String getNomeTitular() {
        return nomeTitular;
    }

    /**
     * Define o nome do titular da conta.
     * @param nomeTitular O novo nome do titular da conta.
     */
    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    /**
     * Obtém o CPF do titular da conta.
     * @return O CPF do titular da conta.
     */
    public String getCpfTitular() {
        return cpfTitular;
    }

    /**
     * Define o CPF do titular da conta.
     * @param cpfTitular O novo CPF do titular da conta.
     */
    public void setCpfTitular(String cpfTitular) {
        this.cpfTitular = cpfTitular;
    }

    /**
     * Obtém a data de abertura da conta.
     * @return A data de abertura da conta.
     */
    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    /**
     * Define a data de abertura da conta.
     * @param dataAbertura A nova data de abertura da conta.
     */
    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    /**
     * Obtém o saldo da conta.
     * @return O saldo da conta.
     */
    public Double getSaldo() {
        return saldo;
    }

    /**
     * Define o saldo da conta.
     * @param saldo O novo saldo da conta.
     */
    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    /**
     * Obtém o status da conta.
     * @return true se a conta estiver ativa, false caso contrário.
     */
    public Boolean getAtiva() {
        return ativa;
    }

    /**
     * Define o status da conta.
     * @param ativa O novo status da conta.
     */
    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    /**
     * Obtém o tipo da conta.
     * @return O tipo da conta.
     */
    public TipoConta getTipo() {
        return tipo;
    }

    /**
     * Define o tipo da conta.
     * @param tipo O novo tipo da conta.
     */
    public void setTipo(TipoConta tipo) {
        this.tipo = tipo;
    }

    /**
     * Representação textual da conta.
     * @return String com as informações da conta.
     */
    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", agencia='" + agencia + '\'' +
                ", nomeTitular='" + nomeTitular + '\'' +
                ", cpfTitular='" + cpfTitular + '\'' +
                ", dataAbertura=" + dataAbertura +
                ", saldo=" + saldo +
                ", ativa=" + ativa +
                ", tipo=" + tipo +
                '}';
    }
}
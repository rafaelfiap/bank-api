package br.com.fiap.bank.model;

/**
 * Enumeração que representa os tipos de conta bancária disponíveis.
 *
 * @author Rafael e Lucas
 * @since 1.0
 * @version 1.1
 */
public enum TipoConta {
    /** Conta corrente: Utilizada para transações diárias. */
    CORRENTE,
    
    /** Conta poupança: Destinada a economias, geralmente com rendimento. */
    POUPANCA,
    
    /** Conta salário: Utilizada para recebimento de salários e benefícios. */
    SALARIO;
}
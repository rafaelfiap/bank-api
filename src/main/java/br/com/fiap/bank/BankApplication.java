package br.com.fiap.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal responsável por iniciar a aplicação Spring Boot.
 *
 * @author Rafael e Lucas
 * @since 1.0
 * @version 1.1
 */
@SpringBootApplication
public class BankApplication {

    /**
     * Método principal que inicia a aplicação.
     *
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }
}

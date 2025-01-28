package io.github.devandref.ms_avaliador_credito.application.exception;

public class DadosClienteNotFoundException extends Exception{

    public DadosClienteNotFoundException() {
        super("Dados do cliente não encontrados para o CPF informado.");
    }

}

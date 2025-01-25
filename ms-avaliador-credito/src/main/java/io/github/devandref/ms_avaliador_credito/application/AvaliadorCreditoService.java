package io.github.devandref.ms_avaliador_credito.application;

import io.github.devandref.ms_avaliador_credito.domain.representation.DadosCliente;
import io.github.devandref.ms_avaliador_credito.domain.representation.SituacaoCliente;
import io.github.devandref.ms_avaliador_credito.infra.clients.ClienteResouceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AvaliadorCreditoService {

    private final ClienteResouceClient clienteResouceClient;

    public AvaliadorCreditoService(ClienteResouceClient clienteResouceClient) {
        this.clienteResouceClient = clienteResouceClient;
    }

    public SituacaoCliente obterSituacaoCliente(String cpf) {
        ResponseEntity<DadosCliente> dadosClienteResponse = clienteResouceClient.dadosClient(cpf);
        return new SituacaoCliente(dadosClienteResponse.getBody(), new ArrayList<>());
    }
}

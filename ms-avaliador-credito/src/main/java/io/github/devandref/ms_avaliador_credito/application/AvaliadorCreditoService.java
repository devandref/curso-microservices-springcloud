package io.github.devandref.ms_avaliador_credito.application;

import io.github.devandref.ms_avaliador_credito.domain.representation.CartaoCliente;
import io.github.devandref.ms_avaliador_credito.domain.representation.DadosCliente;
import io.github.devandref.ms_avaliador_credito.domain.representation.SituacaoCliente;
import io.github.devandref.ms_avaliador_credito.infra.clients.CartoesResourceClient;
import io.github.devandref.ms_avaliador_credito.infra.clients.ClienteResouceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvaliadorCreditoService {

    private final ClienteResouceClient clienteResouceClient;
    private final CartoesResourceClient cartoesResourceClient;

    public AvaliadorCreditoService(ClienteResouceClient clienteResouceClient, CartoesResourceClient cartoesResourceClient) {
        this.clienteResouceClient = clienteResouceClient;
        this.cartoesResourceClient = cartoesResourceClient;
    }

    public SituacaoCliente obterSituacaoCliente(String cpf) {
        ResponseEntity<DadosCliente> dadosClienteResponse = clienteResouceClient.dadosClient(cpf);
        ResponseEntity<List<CartaoCliente>> cartoesByCliente = cartoesResourceClient.getCartoesByCliente(cpf);
        return new SituacaoCliente(dadosClienteResponse.getBody(), cartoesByCliente.getBody());
    }
}

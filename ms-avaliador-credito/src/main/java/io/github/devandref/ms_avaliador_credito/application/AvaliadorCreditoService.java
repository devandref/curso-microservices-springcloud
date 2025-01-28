package io.github.devandref.ms_avaliador_credito.application;

import feign.FeignException;
import io.github.devandref.ms_avaliador_credito.application.exception.DadosClienteNotFoundException;
import io.github.devandref.ms_avaliador_credito.application.exception.ErroComunicacaoMicroserviceException;
import io.github.devandref.ms_avaliador_credito.domain.representation.*;
import io.github.devandref.ms_avaliador_credito.infra.clients.CartoesResourceClient;
import io.github.devandref.ms_avaliador_credito.infra.clients.ClienteResouceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliadorCreditoService {

    private final ClienteResouceClient clienteResouceClient;
    private final CartoesResourceClient cartoesResourceClient;

    public AvaliadorCreditoService(ClienteResouceClient clienteResouceClient,
                                   CartoesResourceClient cartoesResourceClient) {
        this.clienteResouceClient = clienteResouceClient;
        this.cartoesResourceClient = cartoesResourceClient;
    }

    public SituacaoCliente obterSituacaoCliente(String cpf) throws DadosClienteNotFoundException, ErroComunicacaoMicroserviceException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResouceClient.dadosClient(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesByCliente = cartoesResourceClient.getCartoesByCliente(cpf);
            return new SituacaoCliente(dadosClienteResponse.getBody(), cartoesByCliente.getBody());
        } catch (FeignException.FeignClientException e) {
            var status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroserviceException(e.getMessage(), Integer.valueOf(e.status()));
        }
    }

    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda) throws DadosClienteNotFoundException, ErroComunicacaoMicroserviceException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResouceClient.dadosClient(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = cartoesResourceClient.getCartoesRendaAteh(renda);

            List<Cartao> cartoes = cartoesResponse.getBody();
            List<CartaoAprovado> cartaoAprovados = cartoes.stream().map(cartao -> {
                DadosCliente dadosCliente = dadosClienteResponse.getBody();

                BigDecimal limiteBasico = cartao.getLimiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());
                var fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);


                CartaoAprovado aprovado = new CartaoAprovado();
                aprovado.setCartao(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(limiteAprovado);

                return aprovado;
            }).collect(Collectors.toList());

            return new RetornoAvaliacaoCliente(cartaoAprovados);

        } catch (FeignException.FeignClientException e) {
            var status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroserviceException(e.getMessage(), Integer.valueOf(e.status()));
        }
    }
}

package io.github.devandref.ms_cartoes.application;

import io.github.devandref.ms_cartoes.application.representation.CartaoSaveRequest;
import io.github.devandref.ms_cartoes.application.representation.CartoesPorClienteResponse;
import io.github.devandref.ms_cartoes.domain.Cartao;
import io.github.devandref.ms_cartoes.domain.ClienteCartao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
public class CartoesResource {

    private final CartaoService cartaoService;
    private final ClienteCartaoService clienteCartaoService;

    public CartoesResource(CartaoService cartaoService, ClienteCartaoService clienteCartaoService) {
        this.cartaoService = cartaoService;
        this.clienteCartaoService = clienteCartaoService;
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody CartaoSaveRequest cartaoSaveRequest) {
        var cartao = cartaoSaveRequest.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam("renda") Long renda) {
        var listCartao = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(listCartao);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@PathVariable("cpf") String cpf) {
        List<ClienteCartao> clienteCartaos = clienteCartaoService.listCartoesByCpf(cpf);
        var cartoesPorClienteResponse = clienteCartaos.stream()
                .map(CartoesPorClienteResponse::fromModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cartoesPorClienteResponse);
    }


}

package io.github.devandref.ms_cartoes.application;

import io.github.devandref.ms_cartoes.application.representation.CartaoSaveRequest;
import io.github.devandref.ms_cartoes.domain.Cartao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartoes")
public class CartoesResource {

    private final CartaoService cartaoService;

    public CartoesResource(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }


    @PostMapping
    public ResponseEntity cadastrar(@RequestBody CartaoSaveRequest cartaoSaveRequest) {
        var cartao = cartaoSaveRequest.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Cartao>> getCartoesRendaAteh(@RequestParam("renda") Long renda) {
        var listCartao = cartaoService.getCartoesRendaMenorIgual(renda);
        return ResponseEntity.ok(listCartao);
    }

}

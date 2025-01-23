package io.github.devandref.ms_cartoes.application;

import io.github.devandref.ms_cartoes.application.representation.CartaoSaveRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cartoes")
public class CartoesResource {

    private final CartaoService cartaoService;

    public CartoesResource(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @GetMapping
    public String status() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody CartaoSaveRequest cartaoSaveRequest) {
        var cartao = cartaoSaveRequest.toModel();
        cartaoService.save(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

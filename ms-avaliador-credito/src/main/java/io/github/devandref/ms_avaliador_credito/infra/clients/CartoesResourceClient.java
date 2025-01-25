package io.github.devandref.ms_avaliador_credito.infra.clients;

import io.github.devandref.ms_avaliador_credito.domain.representation.CartaoCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "ms-cartoes", path = "/cartoes")
public interface CartoesResourceClient {

    @GetMapping(path = "{cpf}")
    ResponseEntity<List<CartaoCliente>> getCartoesByCliente(@PathVariable("cpf") String cpf);

}

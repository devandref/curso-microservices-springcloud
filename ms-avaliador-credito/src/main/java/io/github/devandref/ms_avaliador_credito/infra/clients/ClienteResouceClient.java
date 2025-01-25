package io.github.devandref.ms_avaliador_credito.infra.clients;

import io.github.devandref.ms_avaliador_credito.domain.representation.DadosCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "ms-clientes", path = "/clientes")
public interface ClienteResouceClient {

    @GetMapping(params = "cpf")
    ResponseEntity<DadosCliente> dadosClient(@RequestParam("cpf") String cpf);

}

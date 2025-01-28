package io.github.devandref.ms_avaliador_credito.application;

import io.github.devandref.ms_avaliador_credito.application.exception.DadosClienteNotFoundException;
import io.github.devandref.ms_avaliador_credito.application.exception.ErroComunicacaoMicroserviceException;
import io.github.devandref.ms_avaliador_credito.domain.representation.DadosAvaliacao;
import io.github.devandref.ms_avaliador_credito.domain.representation.RetornoAvaliacaoCliente;
import io.github.devandref.ms_avaliador_credito.domain.representation.SituacaoCliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

    private final AvaliadorCreditoService avaliadorCreditoService;

    public AvaliadorCreditoController(AvaliadorCreditoService avaliadorCreditoService) {
        this.avaliadorCreditoService = avaliadorCreditoService;
    }

    @GetMapping
    public String status() {
        return "OK";
    }

    @GetMapping(value = "situacao-cliente", params = "cpf")
    public ResponseEntity consultaSituacaoCliente(@RequestParam("cpf") String cpf) {
        try {
            SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (ErroComunicacaoMicroserviceException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatusHttp())).body(e.getMessage());
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dadosAvaliacao) {
        try {
            RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacao(dadosAvaliacao.getCpf(), dadosAvaliacao.getRenda());
            return ResponseEntity.ok(retornoAvaliacaoCliente);
        } catch (ErroComunicacaoMicroserviceException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatusHttp())).body(e.getMessage());
        } catch (DadosClienteNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

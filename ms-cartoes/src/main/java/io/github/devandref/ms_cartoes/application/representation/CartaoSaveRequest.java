package io.github.devandref.ms_cartoes.application.representation;

import io.github.devandref.ms_cartoes.domain.BandeiraCartao;
import io.github.devandref.ms_cartoes.domain.Cartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoSaveRequest {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel() {
        return new Cartao(nome, bandeira, renda, limite);
    }

}

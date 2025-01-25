package io.github.devandref.ms_cartoes.application.representation;

import io.github.devandref.ms_cartoes.domain.ClienteCartao;

import java.math.BigDecimal;

public class CartoesPorClienteResponse {

    private String nome, bandeira;
    private BigDecimal limiteLiberado;

    public CartoesPorClienteResponse(String nome, String bandeira, BigDecimal limiteLiberado) {
        this.nome = nome;
        this.bandeira = bandeira;
        this.limiteLiberado = limiteLiberado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBandeira() {
        return bandeira;
    }

    public void setBandeira(String bandeira) {
        this.bandeira = bandeira;
    }

    public BigDecimal getLimiteLiberado() {
        return limiteLiberado;
    }

    public void setLimiteLiberado(BigDecimal limiteLiberado) {
        this.limiteLiberado = limiteLiberado;
    }

    public static CartoesPorClienteResponse fromModel(ClienteCartao model) {
        return new CartoesPorClienteResponse(model.getCartao().getNome(),
                                             model.getCartao().getBandeira().name(),
                                             model.getLimite());
    }
}

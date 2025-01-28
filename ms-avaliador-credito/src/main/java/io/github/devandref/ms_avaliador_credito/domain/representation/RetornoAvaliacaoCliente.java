package io.github.devandref.ms_avaliador_credito.domain.representation;

import java.util.List;

public class RetornoAvaliacaoCliente {

    private List<CartaoAprovado> cartaoAprovado;

    public RetornoAvaliacaoCliente(List<CartaoAprovado> cartaoAprovado) {
        this.cartaoAprovado = cartaoAprovado;
    }

    public List<CartaoAprovado> getCartaoAprovado() {
        return cartaoAprovado;
    }

    public void setCartaoAprovado(List<CartaoAprovado> cartaoAprovado) {
        this.cartaoAprovado = cartaoAprovado;
    }
}

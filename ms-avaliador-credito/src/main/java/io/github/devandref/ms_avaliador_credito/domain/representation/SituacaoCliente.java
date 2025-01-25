package io.github.devandref.ms_avaliador_credito.domain.representation;

import java.util.List;

public class SituacaoCliente {

    private DadosCliente cliente;
    private List<CartaoCliente> cartoes;

    public SituacaoCliente(DadosCliente dadosCliente, List<CartaoCliente> cartoes) {
        this.cliente = dadosCliente;
        this.cartoes = cartoes;
    }

    public DadosCliente getCliente() {
        return cliente;
    }

    public void setCliente(DadosCliente cliente) {
        this.cliente = cliente;
    }

    public List<CartaoCliente> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<CartaoCliente> cartoes) {
        this.cartoes = cartoes;
    }
}

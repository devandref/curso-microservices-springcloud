package io.github.devandref.ms_cartoes.application;

import io.github.devandref.ms_cartoes.domain.Cartao;
import io.github.devandref.ms_cartoes.infra.repository.CartaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartaoService {

    private final CartaoRepository cartaoRepository;

    public CartaoService(CartaoRepository cartaoRepository) {
        this.cartaoRepository = cartaoRepository;
    }

    @Transactional
    public Cartao save(Cartao cartao) {
        return cartaoRepository.save(cartao);
    }

    public List<Cartao> getCartoesRendaMenorIgual(Long renda) {
       var rendaBigDecimal =  BigDecimal.valueOf(renda);
       return cartaoRepository.findByRendaLessThanEqual(rendaBigDecimal);
    }

}

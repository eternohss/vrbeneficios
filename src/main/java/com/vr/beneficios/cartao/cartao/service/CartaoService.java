package com.vr.beneficios.cartao.cartao.service;

import com.vr.beneficios.cartao.cartao.model.Cartao;
import com.vr.beneficios.cartao.cartao.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartaoService {

    @Autowired
    private CartaoRepository repository;

    public Cartao salvaCartao(Cartao carta){
        return this.repository.save(carta);
    }

    public Optional<Cartao> buscaCartao(String numeroCartao){
        return this.repository.findByNumeroCartao(numeroCartao);
    }
}

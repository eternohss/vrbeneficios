package com.vr.beneficios.cartao.cartao.facade.impl;

import com.vr.beneficios.cartao.cartao.bo.CartaoBo;
import com.vr.beneficios.cartao.cartao.dto.request.CartaoRequest;
import com.vr.beneficios.cartao.cartao.dto.request.TransacaoRequest;
import com.vr.beneficios.cartao.cartao.dto.response.CartaoResponse;
import com.vr.beneficios.cartao.cartao.dto.response.TransacaoResponse;
import com.vr.beneficios.cartao.cartao.facade.CartaoFacade;
import com.vr.beneficios.cartao.cartao.model.Cartao;
import com.vr.beneficios.cartao.cartao.model.Transacao;
import com.vr.beneficios.cartao.cartao.service.CartaoService;
import com.vr.beneficios.cartao.cartao.service.TransacaoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class CartaoFacadeImpl implements CartaoFacade {

    @Autowired
    private CartaoService cartaoService;

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CartaoBo cartaoBo;

    @Override
    public CartaoResponse criarCartao(CartaoRequest cartaoRequest) {
        Optional<Cartao> cartaoBase = cartaoService.buscaCartao(cartaoRequest.getNumeroCartao());
        cartaoBo.existeCartao(cartaoBase, cartaoRequest);
        Cartao cartao = this.mapper.map(cartaoRequest, Cartao.class);
        cartao.setSaldo(500.0);
        Cartao cartaoSalvo = this.cartaoService.salvaCartao(cartao);
        return this.mapper.map(cartaoSalvo, CartaoResponse.class);
    }

    @Override
    public Double obterSaldo(String numeroCartao) {
        Optional<Cartao> cartaoOptional = cartaoService.buscaCartao(numeroCartao);
        Cartao cartao = cartaoBo.naoExisteCartao(cartaoOptional);
        return cartao.getSaldo();
    }

    @Override
    @Transactional
    public TransacaoResponse realizarTransacao(TransacaoRequest transacaoRequest) {
        Optional<Cartao> cartaoOptional = cartaoService.buscaCartao(transacaoRequest.getNumeroCartao());
        cartaoBo.naoExisteCartao(cartaoOptional);
        cartaoBo.verificaSenhaCartao(cartaoOptional, transacaoRequest.getSenhaCartao());
        Cartao cartao = cartaoBo.verificaSaldoCartao(cartaoOptional, transacaoRequest.getValor());
        Transacao transacao = this.mapper.map(transacaoRequest, Transacao.class);
        Double resultado = cartao.getSaldo() - transacao.getValor();
        cartao.setSaldo(resultado);
        Cartao cartaoSalvo = this.cartaoService.salvaCartao(cartao);
        transacao.setCartao(cartaoSalvo);
        transacaoService.salvaTransacao(transacao);
        return this.mapper.map(transacaoRequest, TransacaoResponse.class);
    }
}

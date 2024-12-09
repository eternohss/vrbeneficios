package com.vr.beneficios.cartao.cartao.facade;

import com.vr.beneficios.cartao.cartao.dto.request.CartaoRequest;
import com.vr.beneficios.cartao.cartao.dto.request.TransacaoRequest;
import com.vr.beneficios.cartao.cartao.dto.response.CartaoResponse;
import com.vr.beneficios.cartao.cartao.dto.response.TransacaoResponse;

public interface CartaoFacade {

    CartaoResponse criarCartao(CartaoRequest cartao);
    Double obterSaldo(String numeroCartao);
    TransacaoResponse realizarTransacao(TransacaoRequest transacao);
}

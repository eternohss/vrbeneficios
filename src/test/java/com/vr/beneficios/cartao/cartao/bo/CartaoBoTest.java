package com.vr.beneficios.cartao.cartao.bo;

import com.vr.beneficios.cartao.cartao.dto.request.CartaoRequest;
import com.vr.beneficios.cartao.cartao.exception.BusinessErroEnum;
import com.vr.beneficios.cartao.cartao.exception.BusinessException;
import com.vr.beneficios.cartao.cartao.model.Cartao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CartaoBoTest {


    private CartaoBo cartaoBo;

    @BeforeEach
    void setUp() {
        cartaoBo = new CartaoBo();
    }

    @Test
    void existeCartao_DeveLancarExcecao_QuandoCartaoJaExiste() {
        CartaoRequest cartaoRequest = new CartaoRequest();
        cartaoRequest.setNumeroCartao("1234567890123456");
        cartaoRequest.setSenha("1234");
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao("1234567890123456");

        Optional<Cartao> cartaoOptional = Optional.of(cartao);

        BusinessException exception = assertThrows(BusinessException.class,
                () -> cartaoBo.existeCartao(cartaoOptional, cartaoRequest));
        assertEquals(BusinessErroEnum.CARTAO_JA_EXISTE, exception.getBusinessErroEnum());
    }

    @Test
    void existeCartao_NaoDeveLancarExcecao_QuandoCartaoNaoExiste() {

        CartaoRequest cartaoRequest = new CartaoRequest();
        cartaoRequest.setNumeroCartao("1234567890123456");
        cartaoRequest.setSenha("1234");
        Optional<Cartao> cartaoOptional = Optional.empty();

        assertDoesNotThrow(() -> cartaoBo.existeCartao(cartaoOptional, cartaoRequest));
    }

    @Test
    void naoExisteCartao_DeveRetornarCartao_QuandoCartaoExiste() {
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao("1234567890123456");

        Optional<Cartao> cartaoOptional = Optional.of(cartao);

        Cartao resultado = cartaoBo.naoExisteCartao(cartaoOptional);

        assertNotNull(resultado);
        assertEquals("1234567890123456", resultado.getNumeroCartao());
    }

    @Test
    void naoExisteCartao_DeveLancarExcecao_QuandoCartaoNaoExiste() {

        Optional<Cartao> cartaoOptional = Optional.empty();

        BusinessException exception = assertThrows(BusinessException.class,
                () -> cartaoBo.naoExisteCartao(cartaoOptional));
        assertEquals(BusinessErroEnum.CARTAO_INEXISTENTE, exception.getBusinessErroEnum());
    }

    @Test
    void verificaSenhaCartao_DeveRetornarCartao_QuandoSenhaEstaCorreta() {
        Cartao cartao = new Cartao();
        cartao.setSenha("1234");

        Optional<Cartao> cartaoOptional = Optional.of(cartao);

        Cartao resultado = cartaoBo.verificaSenhaCartao(cartaoOptional, "1234");

        assertNotNull(resultado);
        assertEquals("1234", resultado.getSenha());
    }

    @Test
    void verificaSenhaCartao_DeveLancarExcecao_QuandoSenhaEstaIncorreta() {

        Cartao cartao = new Cartao();
        cartao.setSenha("1234");

        Optional<Cartao> cartaoOptional = Optional.of(cartao);

        BusinessException exception = assertThrows(BusinessException.class,
                () -> cartaoBo.verificaSenhaCartao(cartaoOptional, "5678"));
        assertEquals(BusinessErroEnum.SENHA_INVALIDA, exception.getBusinessErroEnum());
    }

    @Test
    void verificaSaldoCartao_DeveRetornarCartao_QuandoSaldoSuficiente() {
        // Arrange
        Cartao cartao = new Cartao();
        cartao.setSaldo(100.0);

        Optional<Cartao> cartaoOptional = Optional.of(cartao);

        Cartao resultado = cartaoBo.verificaSaldoCartao(cartaoOptional, 50.0);

        assertNotNull(resultado);
        assertEquals(100.0, resultado.getSaldo());
    }

    @Test
    void verificaSaldoCartao_DeveLancarExcecao_QuandoSaldoInsuficiente() {
        Cartao cartao = new Cartao();
        cartao.setSaldo(30.0);

        Optional<Cartao> cartaoOptional = Optional.of(cartao);

        BusinessException exception = assertThrows(BusinessException.class,
                () -> cartaoBo.verificaSaldoCartao(cartaoOptional, 50.0));
        assertEquals(BusinessErroEnum.SALDO_INSUFICIENTE, exception.getBusinessErroEnum());
    }
}

package com.vr.beneficios.cartao.cartao.service;


import com.vr.beneficios.cartao.cartao.model.Cartao;
import com.vr.beneficios.cartao.cartao.repository.CartaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CartaoServiceTest {

    @Mock
    private CartaoRepository repository;

    @InjectMocks
    private CartaoService cartaoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salvaCartao_DeveSalvarECartaoRetornado() {

        Cartao cartao = new Cartao();
        cartao.setNumeroCartao("1234567890123456");
        cartao.setSenha("1234");
        cartao.setSaldo(100.0);

        when(repository.save(cartao)).thenReturn(cartao);

        Cartao cartaoSalvo = cartaoService.salvaCartao(cartao);

        assertNotNull(cartaoSalvo);
        assertEquals("1234567890123456", cartaoSalvo.getNumeroCartao());
        assertEquals("1234", cartaoSalvo.getSenha());
        assertEquals(100.0, cartaoSalvo.getSaldo());
        verify(repository, times(1)).save(cartao);
    }

    @Test
    void buscaCartao_DeveRetornarCartaoQuandoEncontrado() {

        String numeroCartao = "1234567890123456";
        Cartao cartao = new Cartao();
        cartao.setNumeroCartao(numeroCartao);
        cartao.setSenha("1234");
        cartao.setSaldo(100.0);

        when(repository.findByNumeroCartao(numeroCartao)).thenReturn(Optional.of(cartao));

        Optional<Cartao> cartaoEncontrado = cartaoService.buscaCartao(numeroCartao);


        assertTrue(cartaoEncontrado.isPresent());
        assertEquals(numeroCartao, cartaoEncontrado.get().getNumeroCartao());
        verify(repository, times(1)).findByNumeroCartao(numeroCartao);
    }

    @Test
    void buscaCartao_DeveRetornarVazioQuandoCartaoNaoEncontrado() {
        String numeroCartao = "1234567890123456";
        when(repository.findByNumeroCartao(numeroCartao)).thenReturn(Optional.empty());

        Optional<Cartao> cartaoEncontrado = cartaoService.buscaCartao(numeroCartao);

        assertFalse(cartaoEncontrado.isPresent());
        verify(repository, times(1)).findByNumeroCartao(numeroCartao);
    }
}

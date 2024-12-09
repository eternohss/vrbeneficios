package com.vr.beneficios.cartao.cartao.service;

import com.vr.beneficios.cartao.cartao.model.Transacao;
import com.vr.beneficios.cartao.cartao.repository.TransacaoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;

    @Mock
    private TransacaoRepository transacaoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSalvaTransacaoComSucesso() {

        Transacao transacao = new Transacao();
        transacao.setId(1L);
        transacao.setValor(100.0);

        Mockito.when(transacaoRepository.save(Mockito.any(Transacao.class))).thenReturn(transacao);
        Transacao resultado = transacaoService.salvaTransacao(transacao);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1L, resultado.getId());
        Assertions.assertEquals(100.0, resultado.getValor());

        Mockito.verify(transacaoRepository, Mockito.times(1)).save(transacao);
    }
}

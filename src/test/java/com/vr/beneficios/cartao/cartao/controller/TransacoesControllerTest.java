package com.vr.beneficios.cartao.cartao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vr.beneficios.cartao.cartao.controler.TransacoesController;
import com.vr.beneficios.cartao.cartao.dto.request.TransacaoRequest;
import com.vr.beneficios.cartao.cartao.dto.response.TransacaoResponse;
import com.vr.beneficios.cartao.cartao.facade.CartaoFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransacoesController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TransacoesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CartaoFacade facade;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testRealizarTransacaoComSucesso() throws Exception {
        TransacaoResponse transacaoResponse = new TransacaoResponse();
        transacaoResponse.setNumeroCartao("1234567890123456");
        transacaoResponse.setSenhaCartao("1234");
        transacaoResponse.setValor(50.0);

        Mockito.when(facade.realizarTransacao(Mockito.any(TransacaoRequest.class))).thenReturn(transacaoResponse);

        TransacaoRequest transacaoRequest = new TransacaoRequest();
        transacaoRequest.setNumeroCartao("1234567890123456");
        transacaoRequest.setSenhaCartao("1234");
        transacaoRequest.setValor(50.0);

        mockMvc.perform(post("/transacoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(transacaoRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numeroCartao").value("1234567890123456"))
                .andExpect(jsonPath("$.senhaCartao").value("1234"))
                .andExpect(jsonPath("$.valor").value(50.0));
    }

}

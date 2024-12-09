package com.vr.beneficios.cartao.cartao.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vr.beneficios.cartao.cartao.controler.CartaoController;
import com.vr.beneficios.cartao.cartao.dto.request.CartaoRequest;
import com.vr.beneficios.cartao.cartao.dto.response.CartaoResponse;
import com.vr.beneficios.cartao.cartao.facade.CartaoFacade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CartaoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(SpringExtension.class)
public class CartaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CartaoFacade facade;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void criarCartao_Sucesso() throws Exception {
        CartaoRequest request = new CartaoRequest();
        request.setNumeroCartao("123456789");
        request.setSenha("1234");

        CartaoResponse response = new CartaoResponse();
        response.setNumeroCartao("123456789");
        response.setSaldo(100.0);

        when(facade.criarCartao(any(CartaoRequest.class))).thenReturn(response);

        mockMvc.perform(post("/cartoes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.numeroCartao").value("123456789"))
                .andExpect(jsonPath("$.saldo").value(100.0));
    }

    @Test
    void obterSaldo_Sucesso() throws Exception {
        when(facade.obterSaldo("123456789")).thenReturn(100.0);

        mockMvc.perform(get("/cartoes/123456789")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0"));
    }

}
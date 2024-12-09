package com.vr.beneficios.cartao.cartao.dto.response;

import lombok.Data;

@Data
public class TransacaoResponse {
    private String numeroCartao;
    private String senhaCartao;
    private Double valor;
}

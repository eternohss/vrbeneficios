package com.vr.beneficios.cartao.cartao.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum BusinessErroEnum {

    RUN_TIME_ERROR(500, "RUN_TIME_ERROR"),
    CARTAO_JA_EXISTE(422, "CARTAO_JA_EXISTE"),
    CARTAO_INEXISTENTE(404, "CARTAO_INEXISTENTE"),
    SALDO_INSUFICIENTE(400, "SALDO_INSUFICIENTE"),
    SENHA_INVALIDA(400, "SENHA_INVALIDA");
    private Integer statusCode;
    private String valor;
}

package com.vr.beneficios.cartao.cartao.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -6425620447255243845L;

    private Integer statusCode;
    private Object[] args;
    private BusinessErroEnum businessErroEnum;

    public BusinessException(BusinessErroEnum businessErroEnum, Object... args){

        this.statusCode = businessErroEnum.getStatusCode();
        this.args = args;
        this.businessErroEnum = businessErroEnum;
    }

}

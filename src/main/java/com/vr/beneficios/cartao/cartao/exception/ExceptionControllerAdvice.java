package com.vr.beneficios.cartao.cartao.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @Autowired
    private MessageSource messageSource;


    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    ResponseEntity<Object> handlerPessoaException(BusinessException e){

        return ResponseEntity.status(e.getStatusCode())
                .body(e.getArgs());
    }

}

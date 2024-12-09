package com.vr.beneficios.cartao.cartao.bo;

import com.vr.beneficios.cartao.cartao.dto.request.CartaoRequest;
import com.vr.beneficios.cartao.cartao.exception.BusinessErroEnum;
import com.vr.beneficios.cartao.cartao.exception.BusinessException;
import com.vr.beneficios.cartao.cartao.model.Cartao;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class CartaoBo {

    public void existeCartao(Optional<Cartao> cartaoOptional, CartaoRequest cartaoRequest){
        cartaoOptional.ifPresent(cartao -> {
            throw new BusinessException(BusinessErroEnum.CARTAO_JA_EXISTE, cartaoRequest);
        });
    }

    public Cartao naoExisteCartao(Optional<Cartao> cartaoOptional){
        return cartaoOptional
                .orElseThrow(() -> new BusinessException(BusinessErroEnum.CARTAO_INEXISTENTE, BusinessErroEnum.CARTAO_INEXISTENTE.getValor()));
    }

    public Cartao verificaSenhaCartao(Optional<Cartao> cartaoOptional, String senha){
        return cartaoOptional.filter(cartao -> Objects.equals(cartao.getSenha(), senha))
                .orElseThrow(() -> new BusinessException(BusinessErroEnum.SENHA_INVALIDA, BusinessErroEnum.SENHA_INVALIDA.getValor()));
    }

    public Cartao verificaSaldoCartao(Optional<Cartao> cartaoOptional, Double valor){
       return cartaoOptional.filter(cartao -> cartao.getSaldo() >= valor)
                .orElseThrow(() -> new BusinessException(BusinessErroEnum.SALDO_INSUFICIENTE, BusinessErroEnum.SALDO_INSUFICIENTE.getValor()));
    }
}

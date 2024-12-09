package com.vr.beneficios.cartao.cartao.service;

import com.vr.beneficios.cartao.cartao.model.Transacao;
import com.vr.beneficios.cartao.cartao.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;

    public Transacao salvaTransacao(Transacao transacao){
        return this.repository.save(transacao);
    }

}

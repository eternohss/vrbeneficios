package com.vr.beneficios.cartao.cartao.repository;

import com.vr.beneficios.cartao.cartao.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}

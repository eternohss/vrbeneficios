package com.vr.beneficios.cartao.cartao.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "CARTAO")
@Entity
@Data
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CARTAO")
    private Long id;

    @Column(name = "NUMERO_CARTAO")
    private String numeroCartao;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "SALDO")
    private Double saldo;

    @Column(name = "TMS_CADASTRO", nullable = false, updatable = false)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy="cartao", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Transacao> transacaos;

    @PrePersist
    private void prePersist() {
        this.dataCadastro = LocalDateTime.now();
    }
}

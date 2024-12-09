package com.vr.beneficios.cartao.cartao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Table(name = "TRANSACAO")
@Entity
@Data
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TRANSACAO")
    private Long id;

    @Column(name = "VALOR")
    private Double valor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="ID_CARTAO")
    private Cartao cartao;
}

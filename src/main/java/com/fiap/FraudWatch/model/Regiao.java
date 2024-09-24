package com.fiap.FraudWatch.model;

import jakarta.persistence.*;

@Entity
@Table(name = "CH_REGIAO")
public class Regiao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REGIAO")
    private Long id;

    @Column(name = "NOME_REGIAO")
    private String nomeRegiao;

    public Regiao() {
    }

    public Regiao(String regiao) {
        this.nomeRegiao = regiao;
    }

    public Long getId() {
        return id;
    }

    public String getNomeRegiao() {
        return nomeRegiao;
    }
}

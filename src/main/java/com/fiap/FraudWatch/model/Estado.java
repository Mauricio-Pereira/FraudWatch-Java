package com.fiap.FraudWatch.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "CH_ESTADO")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ESTADO")
    private Long id;

    @Column(name = "NOME_ESTADO")
    private String nomeEstado;

    @ManyToOne
    @JoinColumn(name = "ID_REGIAO", referencedColumnName = "ID_REGIAO")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Regiao regiao;

    public Estado(String estado) {
        this.nomeEstado = estado;
    }

    public Estado() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }
}

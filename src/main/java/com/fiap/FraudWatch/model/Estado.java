package com.fiap.FraudWatch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "CH_ESTADO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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



}

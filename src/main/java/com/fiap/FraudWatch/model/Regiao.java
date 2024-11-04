package com.fiap.FraudWatch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CH_REGIAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Regiao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_REGIAO")
    private Long id;

    @Column(name = "NOME_REGIAO")
    private String nomeRegiao;


    public Regiao(String regiao) {
        this.nomeRegiao = regiao;
    }


}

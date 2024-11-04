package com.fiap.FraudWatch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CH_TIPO_USUARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_USUARIO")
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;


}

package com.fiap.FraudWatch.model;

import com.fiap.FraudWatch.model.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "CH_CIDADE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CIDADE")
    private Long id;

    @Column(name = "NOME_CIDADE")
    private String nomeCidade;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Estado estado;

    public Cidade(String cidade) {
        this.nomeCidade = cidade;
    }


}

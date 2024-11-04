package com.fiap.FraudWatch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "CH_BAIRRO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bairro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BAIRRO")
    private Long id;

    @Column(name = "NOME_BAIRRO")
    private String nomeBairro;

    @ManyToOne
    @JoinColumn(name = "ID_CIDADE", referencedColumnName = "ID_CIDADE")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Cidade cidade;

    public Bairro(String bairro) {
        this.nomeBairro = bairro;
    }


}

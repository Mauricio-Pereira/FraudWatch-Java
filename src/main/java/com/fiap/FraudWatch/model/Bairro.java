package com.fiap.FraudWatch.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "CH_BAIRRO")
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

    public Bairro() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeBairro() {
        return nomeBairro;
    }

    public void setNomeBairro(String nomeBairro) {
        this.nomeBairro = nomeBairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}

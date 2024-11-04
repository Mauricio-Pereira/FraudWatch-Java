package com.fiap.FraudWatch.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "CH_ENDERECO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_ENDERECO")
    private Long id;
    @Column(name = "CEP")
    private String cep;
    @Column(name = "LOGRADOURO")
    private String logradouro;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "COMPLEMENTO")
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "ID_BAIRRO", referencedColumnName = "ID_BAIRRO")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Bairro bairro;


}

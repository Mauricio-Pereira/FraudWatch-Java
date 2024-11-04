package com.fiap.FraudWatch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "CH_USUARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_USUARIO")
    private Long id;

    @Column(name="NOME")
    private String nome;

    @Column(name="SOBRENOME")
    private String sobrenome;

    @Column(name="EMAIL")
    private String email;

    @Column(name="SENHA")
    private String senha;

    @Column(name="CPF")
    private String cpf;

    @Column(name="DATA_NASCIMENTO")
    @Temporal(TemporalType.DATE)
    private LocalDate dataNascimento;

    @Column(name="TELEFONE")
    private String telefone;

    @Column(name="DATA_CRIACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_USUARIO", referencedColumnName = "ID_TIPO_USUARIO")
    private TipoUsuario tipoUsuario;

    @ManyToOne
    @JoinColumn(name = "ID_ENDERECO", referencedColumnName = "ID_ENDERECO")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Endereco endereco;


}

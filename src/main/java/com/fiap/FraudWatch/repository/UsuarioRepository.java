package com.fiap.FraudWatch.repository;

import com.fiap.FraudWatch.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCpf(String cpf);
    Optional<Usuario> findByEmail(String email);

    boolean existsByEnderecoId(Long enderecoId);


}

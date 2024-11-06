package com.fiap.FraudWatch.repository;

import com.fiap.FraudWatch.dto.usuarioDto.UsuarioResponseDTO;
import com.fiap.FraudWatch.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCpf(String cpf);
    Optional<Usuario> findByEmail(String email);

    boolean existsByEnderecoId(Long enderecoId);


    @Modifying
    @Transactional
    @Procedure(procedureName = "update_usuario")
    Usuario updateUsuarioWithProcedure(
            @Param("p_id_usuario") Long idUsuario,
            @Param("p_email") String email,
            @Param("p_senha") String senha,
            @Param("p_nome") String nome,
            @Param("p_sobrenome") String sobrenome,
            @Param("p_cpf") String cpf,
            @Param("p_data_nascimento") java.sql.Date dataNascimento,
            @Param("p_telefone") String telefone,
            @Param("p_id_endereco") Long idEndereco,
            @Param("p_id_tipo_usuario") Long idTipoUsuario
    );


    @Modifying
    @Transactional
    @Procedure(procedureName = "delete_usuario")
    void deleteUsuarioById(Long idUsuario);
}

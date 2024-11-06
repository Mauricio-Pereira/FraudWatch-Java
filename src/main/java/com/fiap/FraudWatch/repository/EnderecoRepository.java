package com.fiap.FraudWatch.repository;

import com.fiap.FraudWatch.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {


    @Query(value = "SELECT E.* FROM CH_ENDERECO E " +
            "INNER JOIN CH_USUARIO U ON E.ID_ENDERECO = U.ID_ENDERECO " +
            "WHERE U.ID_USUARIO = :idUsuario", nativeQuery = true)
    Optional<Endereco> findEnderecoByIdUsuario(@Param("idUsuario") Long idUsuario);

    @Modifying
    @Procedure(procedureName = "update_endereco")
    void updateEnderecoWithProcedure(
            Long p_id_endereco,
            String p_cep,
            String p_numero,
            String p_complemento,
            String p_logradouro,
            String p_nome_bairro,
            String p_nome_cidade,
            String p_nome_estado,
            String p_nome_regiao
    );

    @Modifying
    @Transactional
    @Procedure("delete_endereco")
    void deleteEnderecoById(Long idEndereco);


}

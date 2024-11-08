package com.fiap.FraudWatch.repository;

import com.fiap.FraudWatch.model.Cidade;
import com.fiap.FraudWatch.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    Optional<Cidade> findByNomeCidade(String nomeCidade);
    Optional<Cidade> findByNomeCidadeAndEstado(String nomeCidade, Estado estado);
}

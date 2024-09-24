package com.fiap.FraudWatch.repository;

import com.fiap.FraudWatch.model.Regiao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegiaoRepository extends JpaRepository<Regiao, Long> {
    Optional<Regiao> findByNomeRegiao(String nomeRegiao);
}

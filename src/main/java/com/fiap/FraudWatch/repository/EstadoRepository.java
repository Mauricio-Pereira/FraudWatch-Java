package com.fiap.FraudWatch.repository;

import com.fiap.FraudWatch.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    Optional<Estado> findByNomeEstado(String nomeEstado);
}

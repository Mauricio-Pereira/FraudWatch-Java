package com.fiap.FraudWatch.repository;

import com.fiap.FraudWatch.model.Bairro;
import com.fiap.FraudWatch.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {
    Optional<Bairro> findByNomeBairro(String nomeBairro);
    Optional<Bairro> findByNomeBairroAndCidade(String nomeBairro, Cidade cidade);

}

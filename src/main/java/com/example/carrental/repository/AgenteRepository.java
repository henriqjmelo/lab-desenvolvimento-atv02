package com.example.carrental.repository;

import com.example.carrental.model.Agente;
import com.example.carrental.model.TipoAgente;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgenteRepository extends JpaRepository<Agente, Long> {
    List<Agente> findByTipo(TipoAgente tipo);
    Optional<Agente> findByCnpj(String cnpj);
}


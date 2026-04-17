package com.example.carrental.repository;

import com.example.carrental.model.Empregador;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface EmpregadorRepository extends JpaRepository<Empregador, Long> {
    List<Empregador> findByClienteId(Long clienteId);
}


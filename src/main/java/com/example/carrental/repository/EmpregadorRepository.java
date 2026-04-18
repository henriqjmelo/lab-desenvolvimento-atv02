package com.example.carrental.repository;

import com.example.carrental.model.Empregador;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface EmpregadorRepository extends JpaRepository<Empregador, Long> {
    @Query("SELECT e FROM Empregador e WHERE e.cliente.id = :clienteId")
    List<Empregador> findByClienteId(Long clienteId);
}


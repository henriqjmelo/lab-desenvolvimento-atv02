package com.example.carrental.repository;

import com.example.carrental.model.PedidoAluguel;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface PedidoAluguelRepository extends JpaRepository<PedidoAluguel, Long> {
    List<PedidoAluguel> findByParecer(String parecer);
    List<PedidoAluguel> findByStatus(String status);

    @Query("SELECT p FROM PedidoAluguel p WHERE p.cliente.id = :clienteId")
    List<PedidoAluguel> findByClienteId(Long clienteId);
}


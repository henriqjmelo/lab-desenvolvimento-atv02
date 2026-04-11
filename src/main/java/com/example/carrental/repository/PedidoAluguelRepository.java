package com.example.carrental.repository;

import com.example.carrental.model.PedidoAluguel;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface PedidoAluguelRepository extends JpaRepository<PedidoAluguel, Long> {
}

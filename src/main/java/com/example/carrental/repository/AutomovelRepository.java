package com.example.carrental.repository;

import com.example.carrental.model.Automovel;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
}

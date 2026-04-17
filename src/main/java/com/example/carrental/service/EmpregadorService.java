package com.example.carrental.service;

import com.example.carrental.model.Empregador;
import com.example.carrental.repository.EmpregadorRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
public class EmpregadorService {

    private final EmpregadorRepository empregadorRepository;

    public EmpregadorService(EmpregadorRepository empregadorRepository) {
        this.empregadorRepository = empregadorRepository;
    }

    public void save(Empregador empregador) {
        empregadorRepository.save(empregador);
    }

    public Optional<Empregador> findById(Long id) {
        return empregadorRepository.findById(id);
    }

    public List<Empregador> findAll() {
        return StreamSupport.stream(empregadorRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        empregadorRepository.deleteById(id);
    }

    public List<Empregador> findByClienteId(Long clienteId) {
        return empregadorRepository.findByClienteId(clienteId);
    }
}


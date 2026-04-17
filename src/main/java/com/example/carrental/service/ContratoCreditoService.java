package com.example.carrental.service;

import com.example.carrental.model.ContratoCredito;
import com.example.carrental.repository.ContratoCreditoRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
public class ContratoCreditoService {

    private final ContratoCreditoRepository contratoCreditoRepository;

    public ContratoCreditoService(ContratoCreditoRepository contratoCreditoRepository) {
        this.contratoCreditoRepository = contratoCreditoRepository;
    }

    public void save(ContratoCredito contratoCredito) {
        contratoCreditoRepository.save(contratoCredito);
    }

    public Optional<ContratoCredito> findById(Long id) {
        return contratoCreditoRepository.findById(id);
    }

    public List<ContratoCredito> findAll() {
        return StreamSupport.stream(contratoCreditoRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        contratoCreditoRepository.deleteById(id);
    }

    public Optional<ContratoCredito> findByPedidoAluguelId(Long pedidoAluguelId) {
        return contratoCreditoRepository.findByPedidoAluguelId(pedidoAluguelId);
    }
}


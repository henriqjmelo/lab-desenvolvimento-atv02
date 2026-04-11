package com.example.carrental.service;

import com.example.carrental.model.PedidoAluguel;
import com.example.carrental.repository.PedidoAluguelRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class PedidoAluguelService {

    private final PedidoAluguelRepository pedidoAluguelRepository;

    public PedidoAluguelService(PedidoAluguelRepository pedidoAluguelRepository) {
        this.pedidoAluguelRepository = pedidoAluguelRepository;
    }

    public List<PedidoAluguel> findAll() {
        return pedidoAluguelRepository.findAll();
    }

    public Optional<PedidoAluguel> findById(Long id) {
        return pedidoAluguelRepository.findById(id);
    }

    public PedidoAluguel save(PedidoAluguel pedidoAluguel) {
        return pedidoAluguelRepository.save(pedidoAluguel);
    }

    public void deleteById(Long id) {
        pedidoAluguelRepository.deleteById(id);
    }
}

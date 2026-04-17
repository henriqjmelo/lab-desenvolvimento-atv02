package com.example.carrental.service;

import com.example.carrental.model.Agente;
import com.example.carrental.model.TipoAgente;
import com.example.carrental.repository.AgenteRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Singleton
public class AgenteService {

    private final AgenteRepository agenteRepository;

    public AgenteService(AgenteRepository agenteRepository) {
        this.agenteRepository = agenteRepository;
    }

    public void save(Agente agente) {
        agenteRepository.save(agente);
    }

    public Optional<Agente> findById(Long id) {
        return agenteRepository.findById(id);
    }

    public List<Agente> findAll() {
        return StreamSupport.stream(agenteRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        agenteRepository.deleteById(id);
    }

    public List<Agente> findByTipo(TipoAgente tipo) {
        return agenteRepository.findByTipo(tipo);
    }

    public Optional<Agente> findByCnpj(String cnpj) {
        return agenteRepository.findByCnpj(cnpj);
    }

    public List<Agente> findBancos() {
        return agenteRepository.findByTipo(TipoAgente.BANCO);
    }

    public List<Agente> findEmpresas() {
        return agenteRepository.findByTipo(TipoAgente.EMPRESA);
    }
}


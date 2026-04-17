package com.example.carrental.service;

import com.example.carrental.model.Automovel;
import com.example.carrental.repository.AutomovelRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class AutomovelService {

    private final AutomovelRepository automovelRepository;

    public AutomovelService(AutomovelRepository automovelRepository) {
        this.automovelRepository = automovelRepository;
    }

    public List<Automovel> findAll() {
        return automovelRepository.findAll();
    }

    public Optional<Automovel> findById(Long id) {
        return automovelRepository.findById(id);
    }

    public Automovel save(Automovel automovel) {
        return automovelRepository.save(automovel);
    }

    public void deleteById(Long id) {
        automovelRepository.deleteById(id);
    }
}


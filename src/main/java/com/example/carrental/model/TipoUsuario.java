package com.example.carrental.model;

public enum TipoUsuario {
    CLIENTE("Cliente"),
    BANCO("Banco"),
    EMPRESA("Empresa");

    private final String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}


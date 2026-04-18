package com.example.carrental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class ContratoCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double valorCredito;
    private Double taxaJuro;
    private Integer parcelas;
    private Date dataAssinatura;
    
    @OneToOne
    @JoinColumn(name = "pedido_aluguel_id")
    private PedidoAluguel pedidoAluguel;
    
    @ManyToOne
    @JoinColumn(name = "agente_id")
    private Agente agenteConcessor; // Banco que concedeu o credito
    
    private String statusContrato; // ATIVO, FINALIZADO, CANCELADO

    public ContratoCredito() {
    }

    public ContratoCredito(Double valorCredito, Double taxaJuro, Integer parcelas, PedidoAluguel pedidoAluguel, Agente agenteConcessor) {
        this.valorCredito = valorCredito;
        this.taxaJuro = taxaJuro;
        this.parcelas = parcelas;
        this.pedidoAluguel = pedidoAluguel;
        this.agenteConcessor = agenteConcessor;
        this.dataAssinatura = new Date();
        this.statusContrato = "ATIVO";
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValorCredito() {
        return valorCredito;
    }

    public void setValorCredito(Double valorCredito) {
        this.valorCredito = valorCredito;
    }

    public Double getTaxaJuro() {
        return taxaJuro;
    }

    public void setTaxaJuro(Double taxaJuro) {
        this.taxaJuro = taxaJuro;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public Date getDataAssinatura() {
        return dataAssinatura;
    }

    public void setDataAssinatura(Date dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

    public PedidoAluguel getPedidoAluguel() {
        return pedidoAluguel;
    }

    public void setPedidoAluguel(PedidoAluguel pedidoAluguel) {
        this.pedidoAluguel = pedidoAluguel;
    }

    public Agente getAgenteConcessor() {
        return agenteConcessor;
    }

    public void setAgenteConcessor(Agente agenteConcessor) {
        this.agenteConcessor = agenteConcessor;
    }

    public String getStatusContrato() {
        return statusContrato;
    }

    public void setStatusContrato(String statusContrato) {
        this.statusContrato = statusContrato;
    }
}



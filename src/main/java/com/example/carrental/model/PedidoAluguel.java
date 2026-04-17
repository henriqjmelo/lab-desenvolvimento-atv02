package com.example.carrental.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import java.util.Date;

@Entity
public class PedidoAluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status; // PENDENTE, APROVADO, REJEITADO, CANCELADO
    private Date dataPedido;
    private Date dataRetirada;
    private Date dataDevolucao;
    private Double valorDiaria;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "automovel_id")
    private Automovel automovel;
    
    @ManyToOne
    @JoinColumn(name = "agente_id")
    private Agente agenteAnalista; // Agente responsavel pela analise
    
    private String parecer; // Analise financeira: APROVADO, REJEITADO, PENDENTE
    private String motivoRejeicao;
    
    @OneToOne(mappedBy = "pedidoAluguel")
    private ContratoCredito contratoCredito;

    public PedidoAluguel() {
    }

    public PedidoAluguel(String status, Date dataPedido, Date dataRetirada, Date dataDevolucao, Cliente cliente, Automovel automovel) {
        this.status = status;
        this.dataPedido = dataPedido;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.cliente = cliente;
        this.automovel = automovel;
        this.parecer = "PENDENTE";
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Automovel getAutomovel() {
        return automovel;
    }

    public void setAutomovel(Automovel automovel) {
        this.automovel = automovel;
    }
    
    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public Agente getAgenteAnalista() {
        return agenteAnalista;
    }

    public void setAgenteAnalista(Agente agenteAnalista) {
        this.agenteAnalista = agenteAnalista;
    }

    public String getParecer() {
        return parecer;
    }

    public void setParecer(String parecer) {
        this.parecer = parecer;
    }

    public String getMotivoRejeicao() {
        return motivoRejeicao;
    }

    public void setMotivoRejeicao(String motivoRejeicao) {
        this.motivoRejeicao = motivoRejeicao;
    }

    public ContratoCredito getContratoCredito() {
        return contratoCredito;
    }

    public void setContratoCredito(ContratoCredito contratoCredito) {
        this.contratoCredito = contratoCredito;
    }
}



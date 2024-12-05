package com.misticazoo.misticazoo.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PagamentoBase {

    private double valor;
    private String status;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract void processarPagamento();
}

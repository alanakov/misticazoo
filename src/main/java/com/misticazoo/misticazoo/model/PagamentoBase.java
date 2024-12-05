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

    public class BoletoPagamento extends PagamentoBase {
        @Override
        public void processarPagamento() {
            System.out.println("Processando pagamento com boleto bancário.");
        }
    }

    public class CartaoPagamento extends PagamentoBase {
        @Override
        public void processarPagamento() {
            System.out.println("Processando pagamento com cartão de crédito.");
        }
    }

}

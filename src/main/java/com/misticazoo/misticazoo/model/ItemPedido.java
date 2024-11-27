package com.misticazoo.misticazoo.model;

import jakarta.persistence.*;

    @Entity
    @Table(name = "item_pedido")
    public class ItemPedido {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_item_pedido")
        private Integer id;

        @ManyToOne
        @JoinColumn(name = "id_pedido")
        private Pedido pedido;

        @ManyToOne
        @JoinColumn(name = "id_item")
        private Item item;

        @Column(name = "quantidade")
        private Integer quantidade;

        @Column(name = "preco")
        private double precoTotal;

        public ItemPedido(Pedido pedido, Item item, Integer quantidade) {
            this.id = id;
            this.pedido = pedido;
            this.item = item;
            this.quantidade = quantidade;
            this.precoTotal = precoTotal;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Pedido getPedido() {
            return pedido;
        }

        public void setPedido(Pedido pedido) {
            this.pedido = pedido;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public Integer getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(Integer quantidade) {
            this.quantidade = quantidade;
        }

        public double getPrecoTotal() {
            return precoTotal;
        }

        public void setPrecoTotal(double precoTotal) {
            this.precoTotal = precoTotal;
        }
    }
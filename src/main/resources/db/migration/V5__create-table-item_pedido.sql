create table item_pedido (
    id_item_pedido int primary key auto_increment,
    id_pedido int,
    id_item int,
    quantidade int not null,
    preco decimal(10, 2),
    foreign key (id_pedido) references pedido(id_pedido),
    foreign key (id_item) references item(id_item)
);

create table pedido (
    id_pedido int primary key auto_increment,
    id_usuario int,
    data_pedido timestamp default current_timestamp,
    status varchar(25),
    valor_total decimal(10, 2),
    foreign key (id_usuario) references usuario(id_usuario)
);
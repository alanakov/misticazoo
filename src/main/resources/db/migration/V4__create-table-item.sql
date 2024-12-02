create table item (
    id_item int primary key auto_increment,
    id_categoria int,
    nome varchar(100) not null,
    imagem_url varchar(255),
    descricao varchar(255),
    caracteristicas varchar(255),
    cuidados varchar(255),
    raridade varchar(20),
    preco decimal(10, 2) not null,
    estoque int not null,
    foreign key (id_categoria) references categoria(id_categoria)
);

create table item (
    id_item int primary key auto_increment,
    nome varchar(100) not null,
    descricao text,
    preco decimal(10, 2) not null,
    estoque int not null
);

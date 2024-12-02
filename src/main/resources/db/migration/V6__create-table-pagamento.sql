create table pagamento (
    id_pagamento int primary key auto_increment,
    id_pedido int,
    data_pagamento timestamp default current_timestamp,
    valor decimal(10, 2),
    metodo varchar(25),
    status varchar(25),
    foreign key (id_pedido) references pedido(id_pedido)
);
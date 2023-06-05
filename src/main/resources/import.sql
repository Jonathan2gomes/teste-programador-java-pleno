-- INSERT CUSTOMERS

insert into customer_schema (id, cpf, email, nome, telefone)
values (100, '123', 'john@doe.com', 'John Doe', '777-444-45');

insert into customer_schema (id, cpf, email, nome, telefone)
values (101, '123', 'Agbal@do.com', 'Agbaldo', '777-444-45');

insert into customer_schema (id, cpf, email, nome, telefone)
values (102, '123', 'jurinelson@gmail.com', 'Jurinelson', '777-444-45');

insert into customer_schema (id, cpf, email, nome, telefone)
values (103, '123', 'reidi@gmail.com', 'Reidi', '777-444-45');

-- INSERT PRODUCTS

insert into product_schema (id, valor, descricao, unidade)
values (1000, 2.50, 'Coca-Cola', 'Lata');

insert into product_schema (id, valor, descricao, unidade)
values (1001, 3.50, 'Fanta', 'Lata');

insert into product_schema (id, valor, descricao, unidade)
values (1002, 4.50, 'Sprite', 'Lata');

insert into product_schema (id, valor, descricao, unidade)
values (1003, 5.50, 'Guaraná', 'Lata');

insert into product_schema (id, valor, descricao, unidade)
values (1004, 6.50, 'Pepsi', 'Lata');

insert into product_schema (id, valor, descricao, unidade)
values (1005, 7.50, 'Sukita', 'Lata');

-- INSERT ORDERS

insert into order_schema (dataemissao, valortotal, customer_id, id, descricao)
values ('2023-01-01', 6, 100, 10000, 'Pedido 1');

insert into order_schema (dataemissao, valortotal, customer_id, id, descricao)
values ('2023-01-01', 6, 101, 10001, 'Pedido 2');

-- insert order_schema+product_schema

insert into order_schema_product_schema (listaprodutos_id, order_schema_id)
values (1000, 10000);

insert into order_schema_product_schema (listaprodutos_id, order_schema_id)
values (1001, 10001);

-- insert into order_schema (dataemissao, valortotal, id, descricao, listaprodutos)
-- values ('2023-01-01', 6, 10000, 'Pedido 1', '{1000, 1001}');

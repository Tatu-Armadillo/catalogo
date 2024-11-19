drop schema if exists challenge cascade;

create schema challenge;

create table challenge.products(
    id_product bigserial primary key,
    name varchar(255) not null,
    price numeric(10, 2) not null,
    modelo varchar(255),
    fabricante varchar(255),
    detalhes varchar(255),
    product_code varchar(255) unique not null
);

create table challenge.stocks(
    id_stock bigserial primary key,
    quantity integer not null,
    last_update timestamp not null,
    stock_code varchar(255) unique not null,
    product bigint not null
);

alter table challenge.stocks add constraint fk_products_stocks foreign key (product) references challenge.products (id_product);

INSERT INTO challenge.products (name, price, modelo, fabricante, detalhes, product_code) VALUES
('Produto A', 99.99, 'Modelo A1', 'Fabricante X', 'Detalhes sobre o Produto A', 'd8f7f3fa-ef0b-4df2-9d47-89769419cf43'),
('Produto B', 150.50, 'Modelo B2', 'Fabricante Y', 'Detalhes sobre o Produto B', 'b83aaf3d-e9f7-40e5-8266-3b378df3fc8a'),
('Produto C', 75.00, 'Modelo C3', 'Fabricante Z', 'Detalhes sobre o Produto C', '89fd0166-e4cf-4332-9b19-417d9b97331e'),
('Produto D', 120.00, 'Modelo D4', 'Fabricante X', 'Detalhes sobre o Produto D', '62b931e9-9178-4751-97cd-ec24b9001e92'),
('Produto E', 45.30, 'Modelo E5', 'Fabricante Y', 'Detalhes sobre o Produto E', '7790600f-9d02-4699-970f-28cfc922e58b'),
('Produto F', 200.00, 'Modelo F6', 'Fabricante Z', 'Detalhes sobre o Produto F', '9cf6c3cf-820f-43a1-b519-897ff163b08f'),
('Produto G', 60.75, 'Modelo G7', 'Fabricante X', 'Detalhes sobre o Produto G', '25198b3e-0b92-4fcb-a190-eaa23413ac13'),
('Produto H', 89.99, 'Modelo H8', 'Fabricante Y', 'Detalhes sobre o Produto H', 'a35b5a5d-26d5-462d-9d2e-d7ed45e7bfc4'),
('Produto I', 30.00, 'Modelo I9', 'Fabricante Z', 'Detalhes sobre o Produto I', 'd24fc4b8-f3fa-4c92-8a42-f54f1de228db'),
('Produto J', 110.00, 'Modelo J0', 'Fabricante X', 'Detalhes sobre o Produto J', '62b069c9-e99d-4b1c-9ebd-f65394e70f38');

INSERT INTO challenge.stocks (quantity, last_update, stock_code, product) VALUES
(100, '2024-10-30 10:00:00', '8b89f684-e342-4084-9b6f-462c126cc06b', 1),
(200, '2024-10-30 10:05:00', 'a6e25d60-cae9-47e9-8d3d-dab35f393a0a', 2),
(50, '2024-10-30 10:10:00', 'fd58556b-b905-4782-b097-419cd79b9131', 3),
(75, '2024-10-30 10:15:00', '8c87f5c9-56be-44e2-8783-63a4e413b8ba', 4),
(150, '2024-10-30 10:20:00', 'f73c9262-b587-4875-bd5a-d85062043353', 5),
(25, '2024-10-30 10:25:00', '7fc49d36-24b9-4906-81b1-d6d987e1e9ea', 6),
(300, '2024-10-30 10:30:00', 'b505345a-9241-4410-81a2-6b84c95cc14d', 7),
(80, '2024-10-30 10:35:00', 'd79e89c4-5d87-41a1-8e65-43bb3032d23e', 8),
(60, '2024-10-30 10:40:00', '2997e0f0-bc1d-4f69-8127-11fc0c69dce5', 9),
(90, '2024-10-30 10:45:00', '906c9f91-fdd9-4d19-b75a-30e5ac8d63fc', 10);

create schema challenge;

create table products(
    id_product bigserial primary key,
    name varchar(255) not null,
    price numeric(10, 2) not null,
    modelo varchar(255),
    fabricante varchar(255),
    detalhes varchar(255)
);

create table stocks(
    id_stocks bigserial primary key,
    quantity integer not null,
    lastUpdate timestamp not null,
    product bigint not null
);

alter table stocks add constraint fk_products_stocks foreign key (product) references products (id_product);
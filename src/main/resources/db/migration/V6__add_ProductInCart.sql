create table product_in_cart
(
    id         bigserial not null,
    name varchar(255),
    created    timestamp,
    updated    timestamp,
    product_id int8,
    quantity   int4,
    cost   int4,
    cart_id int8,
    primary key (id)
);

alter table if exists product_in_cart
    add constraint fk_cart_product_in_cart
        foreign key (cart_id)
            references cart;

 DROP TABLE link_cart_product;

 ALTER TABLE cart
 DROP COLUMN order_number;
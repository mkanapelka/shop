create table product_in_order
(
    id         bigserial not null,
    name varchar(255),
    created    timestamp,
    updated    timestamp,
    product_id int8,
    quantity   int4,
    cost   int4,
    order_id int8,
    primary key (id)
);

alter table if exists product_in_order
    add constraint fk_order_product_in_corder
        foreign key (order_id)
            references user_order;

 DROP TABLE link_product_order;

 ALTER TABLE user_order
 DROP COLUMN order_number;